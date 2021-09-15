import React, {Component} from "react";
import {APP_API} from "../helpers/AppRequests";
import {keycloak, userToken} from "../../App";
import {Table} from "semantic-ui-react";
import {helpers} from "../helpers/Helpers";
import {Link} from "react-router-dom";
import Loader from "react-loader-spinner";


class Home extends Component {


    constructor(props) {
        super(props);
        this.state = {
            isLoading: true,
            kits: [],
            articles: []
        }
    }

    async componentDidMount() {
        const kitsResponse = await APP_API.getKitsListFromSylob(userToken)
        const kitBody = await kitsResponse.data
        this.setState({kits: kitBody, isLoading: false})
    }

    handleView = async (kitLabel) => {
        const response = await APP_API.getKitByLabel(kitLabel, userToken);
        const result = await response.data;

        return result
    }

    render() {

        const {kits, isLoading} = this.state

        if (isLoading) {
            return (

                <Loader
                    type="Puff"
                    color="#00BFFF"
                    height={100}
                    width={100}
                    className="position-absolute top-50 start-50 translate-middle"
                />

            );
        } else {
            if (kits.length > 0) {
                return (
                    <div className="container-fluid">

                        <div className="col-12 text-center mb-3 my-sticky">
                            <h3>LISTE DES KIT DISPONIBLES CHEZ {kits[0].socialReason}</h3>
                        </div>
                        <br/>
                        <Table singleLine>
                            <Table.Header>
                                {helpers.isAdmin(keycloak) &&
                                <Table.Row>
                                    <Table.HeaderCell>LIBELLE</Table.HeaderCell>
                                    <Table.HeaderCell> ACTION </Table.HeaderCell>
                                </Table.Row>
                                }
                            </Table.Header>

                            <Table.Body>
                                {kits.map((el, idx) => {
                                    return (
                                        <Table.Row key={idx}>
                                            <Table.Cell>{el.label}</Table.Cell>
                                            <Table.Cell>
                                                <div className="row gutters">
                                                    <div className="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                        <div className="text-right">
                                                            <Link to={`/kitContent/${el.label}`}
                                                                  className="btn btn-secondary">

                                                                VOIR LE CONTENU
                                                            </Link>
                                                        </div>
                                                    </div>
                                                </div>
                                            </Table.Cell>
                                        </Table.Row>
                                    );
                                })}
                            </Table.Body>
                        </Table>
                    </div>
                )
            } else {
                return (<div className="container-fluid">
                    <div className="position-absolute top-50 start-50 translate-middle">No kit</div>
                </div>)
            }
        }
    }
}

export default Home;