import React, {Component} from "react";
import {APP_API} from "../helpers/AppRequests";
import {helpers} from "../helpers/Helpers";
import {userToken} from "../../App";
import {Loader} from "semantic-ui-react";

/**
 * Displays a User profile
 */
class Profile extends Component {

    constructor(props) {
        super(props);
        this.state = {
            isLoading: true,
            profile: null
        }
    }

    async componentDidMount() {
        /**
         * Request for getting the logged User
         * @type {AxiosResponse<any>}
         */
        const request = await APP_API.getUserByEmail(helpers.getUserEmail(), userToken)
        this.setState({profile: request.data, isLoading: false})
    }

    render() {
        if (this.state.profile === null || this.state.isLoading) {
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
            const {firstName, lastName, email, roles, company} = this.state.profile;
            return (
                <div className="container-fluid">
                    <div className="row d-flex justify-content-center">
                        <div className="col-12 text-center">
                            <h3>Profile</h3>
                        </div>
                        <div className="col-xl-6 col-md-12">
                            <div className="card">
                                <div className="row">
                                    <div className="col-sm-4 d-flex align-items-center justify-content-center">
                                        <img
                                            src="https://img.icons8.com/bubbles/100/000000/user.png"
                                            className="rounded-circle"
                                            alt="User-Profile-Image"/>
                                    </div>
                                    <div className="col-sm-8">
                                        <div className="row">
                                            <div className="col-sm-8">
                                                <p className="m-b-10 f-w-600">Full Name :
                                                    <strong> {" " + firstName + " " + lastName} </strong>
                                                </p>
                                            </div>
                                            <div className="col-sm-8">
                                                <p className="m-b-10 f-w-600">Email :
                                                    <strong> {" " + email} </strong>
                                                </p>
                                            </div>
                                            <div className="col-sm-8">
                                                <p className="m-b-10 f-w-600">Roles :
                                                    <strong> {" " + roles} </strong>
                                                </p>
                                            </div>
                                            <div className="col-sm-8">
                                                <p className="m-b-10 f-w-600">Company :
                                                    <strong> {" " + company.socialReason} </strong>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            )
        }
    }
}

export default Profile;