import React from "react";
import {useKeycloak} from "@react-keycloak/web";
import {helpers} from "./Helpers";
import {NavLink} from "react-router-dom";
import {Nav, Navbar} from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

export const LocalNavbar = () => {
    const {keycloak} = useKeycloak();

    const handleLogInOut = () => {
        if(keycloak.authenticated){
           keycloak.logout()
        }else{
            console.log("in login  ")
            keycloak.login()
        }
    }

    const  checkAuthenticated = () => {
        if(!keycloak.authenticated){
            handleLogInOut()
        }
    }

    const getUsername = () => {
        return keycloak.authenticated && keycloak.tokenParsed && keycloak.tokenParsed.preferred_username
    }

    const getLogInOutText = () => {
        return keycloak.authenticated ? "LogOut": "Login"
    }

    const getAdminMenuStyle = () => {
        return keycloak.authenticated && helpers.isAdmin(keycloak) ? {"display": "block"} : {"display": "none"}
    }


    return (
                    <Navbar bg="dark" variant="dark" expand="lg" fixed="top">
                        <Navbar.Collapse id="basic-navbar-nav">
                            <Nav className="mr-auto">
                                {
                                    keycloak.authenticated &&
                                    <Nav.Link href="#home" as={NavLink} exact to='/home'>Home</Nav.Link>
                                }
                                {
                                    keycloak.authenticated &&
                                    <Nav.Link href="#profile" as={NavLink} exact to='/profile'>Profile</Nav.Link>
                                }
                                {helpers.isAdmin(keycloak) &&
                                <Nav.Link href="#userList" as={NavLink} exact to='/userList'>User List</Nav.Link>
                                }
                                {helpers.isAdmin(keycloak) &&
                                <Nav.Link href="#companyList" as={NavLink} exact to='/companyList'>Company
                                    List</Nav.Link>
                                }
                                {helpers.isAdmin(keycloak) &&
                                <Nav.Link href="#addressList" as={NavLink} exact to='/addressList'>Address
                                    List</Nav.Link>
                                }
                                {helpers.isAdmin(keycloak) &&
                                <Nav.Link href="#addAddress" as={NavLink} exact to='/addAddress'>Add Address</Nav.Link>
                                }
                                {helpers.isAdmin(keycloak) &&
                                <Nav.Link href="#addCompany" as={NavLink} exact to='/addCompany'>Add Company</Nav.Link>
                                }
                                {helpers.isAdmin(keycloak) &&
                                <Nav.Link href="#addUser" as={NavLink} exact to='/addUser'>Add User</Nav.Link>
                                }
                                <NavLink as={NavLink} exact to="/login" className="btn btn-danger" onClick={handleLogInOut}>{getLogInOutText()}</NavLink>
                            </Nav>
                        </Navbar.Collapse>
                    </Navbar>
    )
}

export default LocalNavbar