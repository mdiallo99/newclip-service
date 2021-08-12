import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Home from "./components/home/Home";
import Profile from "./components/user/Profile";
import Keycloak from "keycloak-js";
import {config} from "./constants/Constants";
import {APP_API} from "./components/helpers/AppRequests";
import {Dimmer, Header, Icon} from "semantic-ui-react";
import {ReactKeycloakProvider} from "@react-keycloak/web";
import LocalNavbar from "./components/helpers/LocalNavbar";
import PrivateRoute from "./components/helpers/PrivateRoute";
import UserList from "./components/user/UserList";
import CompanyList from "./components/company/CompanyList";
import AddressList from "./components/address/AddressList";
import AddAddress from "./components/address/AddAddress";
import AddCompany from "./components/company/AddCompany";
import AddUser from "./components/user/AddUser";
import KitContent from "./components/home/KitContent";
import {Redirect} from "react-router";

/**
 * The user token. it's used in several pages
 * @type {null}
 */
let userToken = null;
/**
 * Keycloak instance. it's used in several pages for user credential management
 * @type {null}
 */
let keycloak = null;

function App() {
    /**
     * Keycloak instance
     * @type {Keycloak.KeycloakInstance}
     */
    keycloak = new Keycloak({
        url: `${config.url.KEYCLOAK_BASE_URL}/auth`,
        realm: "newclip-services",
        clientId: "authentication-app"
    })
    /**
     * Encoding
     * @type {{pkceMethod: string}}
     */
    const initOptions = {pkceMethod: 'S256'}
    const eventLogger = (event, error) => {
        console.log('onKeycloakEvent', event, error)
    }

    /**
     *  Checks if a user logged or not
     * @param event
     * @returns {Promise<void>}
     */
    const handleOnEvent = async (event) => {
        if (event === 'onAuthSuccess') {
            if (keycloak.authenticated) {
                console.log("Token: ", keycloak.token);
                userToken = keycloak.token;
                let response = await APP_API.getUserMe(keycloak.token)
                if (response.status === 404) {
                    response = await APP_API.saveUserMe(keycloak.token)
                    console.log('Profile created !')
                }
            }
        } else {
            console.log("onError for authentication !")
        }
    }

    const loadingComponent = (<Dimmer inverted active={true} page>
            <Header style={{color: '#4d4d4d'}} as='h2' icon inverted>
                <Icon loading name='cog'/>
                <Header.Content> App loading
                    <Header.Subheader style={{color: '#4d4d4d'}}> or running authorization</Header.Subheader>
                </Header.Content>
            </Header>
        </Dimmer>
    )

    let activeMenu = null;
    const setActiveMenu = (newMenu) => {
        activeMenu = newMenu;
    }

    return (
        <ReactKeycloakProvider
            authClient={keycloak}
            initOptions={initOptions}
            LoadingComponent={loadingComponent}
            onEvent={(event, error) => handleOnEvent(event, error)}
        >
            <Router>
                <LocalNavbar/>
                <section>
                    <Switch>
                        <PrivateRoute path='/home' component={Home}/>
                        <PrivateRoute path='/kitContent' component={KitContent}/>
                        <PrivateRoute path='/profile' component={Profile}/>
                        <PrivateRoute path='/userList' component={UserList}/>
                        <PrivateRoute path='/companyList' component={CompanyList}/>
                        <PrivateRoute path='/addressList' component={AddressList}/>
                        <PrivateRoute path='/addAddress' component={AddAddress}/>
                        <PrivateRoute path='/addCompany' component={AddCompany}/>
                        <PrivateRoute path='/addUser' component={AddUser}/>
                        <Redirect to="/profile"/>
                    </Switch>
                </section>
                <footer></footer>
            </Router>
        </ReactKeycloakProvider>
    );
}

export {App, userToken, keycloak};