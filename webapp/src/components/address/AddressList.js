import React, {Component} from "react";
import {APP_API} from "../helpers/AppRequests";
import {keycloak, userToken} from "../../App";
import {Table} from "semantic-ui-react";
import {helpers} from "../helpers/Helpers";

/**
 * Display the list of addresses
 */
class AddressList extends Component{
    constructor(props) {
        super(props);
        this.state = {
            addresses: []
        }
    }

    async componentDidMount(){
        const response = await APP_API.getAddressList(userToken)
        const body = await response.data
        this.setState({addresses: body})
    }


    render() {
        const {addresses} = this.state
        console.log("addresses : "+JSON.stringify(addresses))

        if(addresses !== null){
            return (
                <>
                    <div className="col-12 text-center">
                        <h3>List of Addresses</h3>
                    </div>
                <Table singleLine>
                    <Table.Header>
                        {helpers.isAdmin(keycloak) &&
                        <Table.Row>
                            <Table.HeaderCell>STREET NUMBER</Table.HeaderCell>
                            <Table.HeaderCell>STREET NAME</Table.HeaderCell>
                            <Table.HeaderCell>ZIP CODE</Table.HeaderCell>
                            <Table.HeaderCell>CITY</Table.HeaderCell>
                            <Table.HeaderCell>COUNTRY</Table.HeaderCell>
                            <Table.HeaderCell>ACTIONS</Table.HeaderCell>
                        </Table.Row>
                        }
                    </Table.Header>

                    <Table.Body>
                        {addresses.map(el => {
                            return (
                                <Table.Row key={el.postalCode}>
                                    <Table.Cell>{el.streetNumber}</Table.Cell>
                                    <Table.Cell>{el.streetName}</Table.Cell>
                                    <Table.Cell>{el.postalCode}</Table.Cell>
                                    <Table.Cell>{el.city}</Table.Cell>
                                    <Table.Cell>{el.country}</Table.Cell>
                                    <Table.Cell>
                                        <div className="row gutters">
                                            <div className="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <div className="text-right">
                                                    <button type="submit"
                                                            id="submit"
                                                            name="submit"
                                                            className="btn btn-secondary">Edit
                                                    </button>
                                                    <button type="button"
                                                            id="clear"
                                                            name="clear"
                                                            className="btn btn-danger">Delete
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </Table.Cell>
                                </Table.Row>
                            );
                        })}
                    </Table.Body>
                </Table>
                </>
            )
        }

        return "Loading"
    }
}

export default AddressList