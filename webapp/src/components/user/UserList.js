import React from "react";
import {APP_API} from "../helpers/AppRequests";
import {keycloak, userToken} from "../../App";
import {Table} from "semantic-ui-react";
import {helpers} from "../helpers/Helpers";

/**
 * Displays the list of users
 */
class UserList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            users: []
        };
    }

    async componentDidMount(){
        const response = await APP_API.getUserList(userToken);
        const body = await response.data;
        this.setState({users: body});
    }

    render() {
        const {users} = this.state;

        return (
            <>
                <div className="col-12 text-center">
                    <h3>List of users</h3>
                </div>
            <Table singleLine>
                <Table.Header>
                    {helpers.isAdmin(keycloak) &&
                    <Table.Row>
                        <Table.HeaderCell>FIRST NAME</Table.HeaderCell>
                        <Table.HeaderCell>LAST NAME</Table.HeaderCell>
                        <Table.HeaderCell>EMAIL</Table.HeaderCell>
                        <Table.HeaderCell>ROLES</Table.HeaderCell>
                        <Table.HeaderCell>COMPANY</Table.HeaderCell>
                        <Table.HeaderCell>CITY</Table.HeaderCell>
                    </Table.Row>
                    }
                </Table.Header>

                <Table.Body>
                    {users.map(el => {
                        return (
                            <Table.Row key={el.email}>
                                <Table.Cell>{el.firstName}</Table.Cell>
                                <Table.Cell>{el.lastName}</Table.Cell>
                                <Table.Cell>{el.email}</Table.Cell>
                                <Table.Cell>{el.roles}</Table.Cell>
                                <Table.Cell>{el.company.socialReason}</Table.Cell>
                                <Table.Cell>{el.company.city}</Table.Cell>
                            </Table.Row>
                        );
                    })}
                </Table.Body>
            </Table>
            </>
        );
    }
}

export default UserList;