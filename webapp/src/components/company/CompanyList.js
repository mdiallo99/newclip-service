import React, {Component} from "react";
import {APP_API} from "../helpers/AppRequests";
import {keycloak, userToken} from "../../App";
import {Table} from "semantic-ui-react";
import {helpers} from "../helpers/Helpers";

class CompanyList extends Component{
    constructor(props) {
        super(props);
        this.state = {
            companies: []
        }
    }
    async componentDidMount(){
        const response = await APP_API.getCompanyList(userToken)
        const body = await response.data
        this.setState({companies: body});
    }

    render() {
        const {companies} = this.state
        if(companies !== null){
            return (
                <>
                <div className="col-12 text-center">
                    <h3>List of Companies</h3>
                </div>
                <Table singleLine>
                    <Table.Header>
                        {helpers.isAdmin(keycloak) &&
                        <Table.Row>
                            <Table.HeaderCell>CODE</Table.HeaderCell>
                            <Table.HeaderCell>SOCIAL REASON</Table.HeaderCell>
                            <Table.HeaderCell>CLIENT TYPE</Table.HeaderCell>
                            <Table.HeaderCell>LABEL</Table.HeaderCell>
                            <Table.HeaderCell>COUNTRY CODE</Table.HeaderCell>
                            <Table.HeaderCell>COUNTRY NAME</Table.HeaderCell>
                            <Table.HeaderCell>ZIP CODE</Table.HeaderCell>
                            <Table.HeaderCell>CITY</Table.HeaderCell>
                            <Table.HeaderCell>RECIPIENT</Table.HeaderCell>
                        </Table.Row>
                        }
                    </Table.Header>

                    <Table.Body>
                        {companies.map((el,i) => {
                            return (
                                <Table.Row key={i}>
                                    <Table.Cell>{el.code}</Table.Cell>
                                    <Table.Cell>{el.socialReason}</Table.Cell>
                                    <Table.Cell>{el.clientType}</Table.Cell>
                                    <Table.Cell>{el.label}</Table.Cell>
                                    <Table.Cell>{el.countryCode}</Table.Cell>
                                    <Table.Cell>{el.countryName}</Table.Cell>
                                    <Table.Cell>{el.zipCode}</Table.Cell>
                                    <Table.Cell>{el.city}</Table.Cell>
                                    <Table.Cell>{el.recipient}</Table.Cell>
                                </Table.Row>
                            );
                        })}
                    </Table.Body>
                </Table>
                </>
            )
        }else{
            return "Loading"
        }

    }
}

export default CompanyList