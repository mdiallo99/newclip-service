import React, {useEffect, useState} from "react";
import {APP_API} from "../helpers/AppRequests";
import {userToken} from "../../App";


const AddUser = () =>  {

    /**
     * Initial state, where all fields are empty
     * @type {{companyCode: string, firstName: string, lastName: string, password: string, companies: *[], roles: string, email: string}}
     */
    const initState = {
        firstName: '',
        lastName: '',
        email:'',
        password: '',
        roles: '',
        companyCode: '',
        companies: []
    }
   const [attributes, setAttributes] = useState(initState)

    useEffect(async ()=> {
        const  response = await APP_API.getCompanyList(userToken);
        attributes.companies = await response.data;
        console.log("Companies: "+response.data)
    })

    /**
     * this function cleans form content
     */
    const handleReset = () => {
        setAttributes(initState);
    }

    /**
     * signs each field content to its attribute
     * @param event content entry
     */
    const changeHandler = (event) => {
        setAttributes({...attributes, [event.target.name]: event.target.value})
    }

    const handleSubmit = (event) => {
        event.preventDefault()

        console.log("Code: "+attributes.companyCode)
        const requestCompany = APP_API.getCompanyByCode(attributes.companyCode, userToken);
        requestCompany.then( res => {
            /**
             * represents a json object for Company creation in Backend
             * @type {{zipCode: (string|string|*), code, clientType: (string|string|*), city: (string|string|string|*), countryCode: (string|string|*), recipient: (string|string|string|*), label, countryName: (string|string|*), socialReason: (string|string|*)}}
             */
                const jsonCompany = {
                    "code": res.data.code,
                    "socialReason": res.data.socialReason,
                    "clientType": res.data.clientType,
                    "label": res.data.label,
                    "countryCode": res.data.countryCode,
                    "countryName": res.data.countryName,
                    "zipCode": res.data.zipCode,
                    "city": res.data.city,
                    "recipient": res.data.recipient
                }

            /**
             * represents a json object for User creation in Backend
             * @type {{firstName: string, lastName: string, password: string, roles: string, company: {zipCode: (string|*), code, clientType: (string|*), city: (string|*), countryCode: (string|*), recipient: (string|*), label, countryName: (string|*), socialReason: (string|*)}, email: string}}
             */
                const jsonUser = {
                    "firstName": attributes.firstName,
                    "lastName": attributes.lastName,
                    "email": attributes.email,
                    "password": attributes.password,
                    "roles": attributes.roles,
                    "company": jsonCompany
                }
                console.log("jsonUser: ", jsonUser)
                console.log("Company: ", jsonCompany)
            /**
             * the request for adding a new User
             * @type {Promise<AxiosResponse<any>>}
             */
                const addUserRequest = APP_API.addUser(jsonUser, userToken);
                addUserRequest.then(res => {
                    console.log("add user response :"+res.data)
                    handleReset()
                }).catch(error => {
                    console.log("add user error :", error)
                })

                console.log("jsonCompany: ", jsonCompany)
            }
        ).catch(error => {
            console.log("Error : "+error)
        });

    }

    return (

            <>
                <form onSubmit={(e) => handleSubmit(e)}>
                    <div className="container">
                        <div className="row gutters">
                            <div className="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
                                <div className="card h-100">
                                    <div className="card-body">
                                        <div className="row gutters">
                                            <div className="col-12 text-center">
                                                <h3>Add a new user</h3>
                                            </div>
                                            <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div className="form-group">
                                                    <label htmlFor="firstName">First name</label>
                                                    <input type="text"
                                                           className="form-control"
                                                           id="firstName"
                                                           name="firstName"
                                                           value={attributes.firstName}
                                                           placeholder="Ex: Dupont"
                                                           required={"required"}
                                                           onChange={(e) => changeHandler(e) }/>
                                                </div>
                                            </div>
                                            <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div className="form-group">
                                                    <label htmlFor="lastName">Last name</label>
                                                    <input type="text"
                                                           className="form-control"
                                                           id="lastName"
                                                           name="lastName"
                                                           value={attributes.lastName}
                                                           placeholder="Ex: Hugo"
                                                           required={"required"}
                                                           onChange={(e) => changeHandler(e) } />
                                                </div>
                                            </div>
                                        </div>
                                        <div className="row gutters">
                                            <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div className="form-group">
                                                    <label htmlFor="email">Email</label>
                                                    <input type="email"
                                                           className="form-control"
                                                           id="email"
                                                           name="email"
                                                           value={attributes.email}
                                                           placeholder="Ex: hugo@exmaple.com"
                                                           required={"required"}
                                                           onChange={(e) => changeHandler(e) }
                                                    />
                                                </div>
                                            </div>
                                            <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div className="form-group">
                                                    <label htmlFor="password">Password</label>
                                                    <input type="password"
                                                           className="form-control"
                                                           id="password"
                                                           name="password"
                                                           value={attributes.password}
                                                           placeholder="Ex: myPassword"
                                                           required={"required"}
                                                           onChange={(e) => changeHandler(e) }
                                                    />
                                                </div>
                                            </div>
                                            <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div className="form-group">
                                                    <div>
                                                        <label htmlFor="roles">Role </label>
                                                        <select className="browser-default custom-select form-control"
                                                                name="roles"
                                                                value={attributes.roles}
                                                                onChange={(e) => changeHandler(e)}
                                                                required={"required"}>
                                                           <option>--Please Select--</option>
                                                            <option value="USER">USER</option>
                                                            <option value="ADMIN">ADMIN</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div className="form-group">
                                                    <div>
                                                        <label htmlFor="company">Company</label>
                                                        <input className="form-control"
                                                                name="companyCode"
                                                                value={attributes.companyCode}
                                                                onChange={(e) => changeHandler(e)}
                                                                required={"required"}/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div className="row gutters">
                                                <div className="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                    <div className="text-right">
                                                        <button type="submit"
                                                                id="submit"
                                                                name="submit"
                                                                className="btn btn-primary">Submit
                                                        </button>
                                                        <button type="button"
                                                                id="clear"
                                                                name="clear"
                                                                onClick={() => handleReset()}
                                                                className="btn btn-warning">Reset
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </>
        )
}

export default AddUser