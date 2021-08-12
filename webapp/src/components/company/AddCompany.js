import React, {useState} from "react";
import {APP_API} from "../helpers/AppRequests";
import {userToken} from "../../App";


const AddCompany = () => {

    /**
     *  Initial state, where all fields are empty
     * @type {{zipCode: string, code: string, clientType: string, city: string, countryCode: string, recipient: string, label: string, countryName: string, socialReason: string}}
     */
    const initState = {
        code: '',
        socialReason: '',
        clientType: '',
        label: '',
        countryCode: '',
        countryName: '',
        zipCode: '',
        city: '',
        recipient: ''
    }
    const [attributes, setAttributes] = useState(initState)

    /**
     * this function cleans form content
     */
    const handleReset = () => {
        setAttributes(initState);
    }

    /**
     * Form submit handler
     * @param event submit
     */
    const handleSubmit = (event) => {
        event.preventDefault();

        const body = APP_API.saveCompany(attributes, userToken);
        body.then(response => {
            handleReset()
        }).catch(onerror => {
            console.log("Promise error: " + onerror)
        })

    }

    /**
     * assigns each field content to its attribute
     * @param event content entry
     */
    const changeHandler = (event) => {
        setAttributes({...attributes, [event.target.name]: event.target.value})
    }

        return (
            <div>
                <form onSubmit={(e) => handleSubmit(e)}>
                    <div className="container">
                        <div className="row gutters">
                            <div className="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
                                <div className="card h-100">
                                    <div className="card-body">
                                        <div className="row gutters">
                                            <div className="col-12 text-center">
                                                <h3>Add a new company</h3>
                                            </div>
                                            <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div className="form-group">
                                                    <label htmlFor="name"> Code </label>
                                                    <input type="text"
                                                           className="form-control"
                                                           id="code"
                                                           name="code"
                                                           value={attributes.code}
                                                           placeholder="Ex : C0000495"
                                                           required={"required"}
                                                           onChange={(e) => changeHandler(e) }/>
                                                </div>
                                            </div>
                                            <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div className="form-group">
                                                    <label htmlFor="name"> Social reason </label>
                                                    <input type="text"
                                                           className="form-control"
                                                           id="socialReason"
                                                           name="socialReason"
                                                           value={attributes.socialReason}
                                                           placeholder="Ex : CLINIQUE BEAU SOLEIL"
                                                           required={"required"}
                                                           onChange={(e) => changeHandler(e) }/>
                                                </div>
                                            </div>
                                            <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div className="form-group">
                                                    <label htmlFor="name"> Client type </label>
                                                    <input type="text"
                                                           className="form-control"
                                                           id="clientType"
                                                           name="clientType"
                                                           value={attributes.clientType}
                                                           placeholder="Ex : Privé"
                                                           required={"required"}
                                                           onChange={(e) => changeHandler(e) }/>
                                                </div>
                                            </div>
                                            <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div className="form-group">
                                                    <label htmlFor="name"> Label </label>
                                                    <input type="text"
                                                           className="form-control"
                                                           id="label"
                                                           name="label"
                                                           value={attributes.label}
                                                           placeholder="Ex : CLIENT FRANCE DIRECT"
                                                           required={"required"}
                                                           onChange={(e) => changeHandler(e) }/>
                                                </div>
                                            </div>
                                            <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div className="form-group">
                                                    <label htmlFor="name"> Country code </label>
                                                    <input type="text"
                                                           className="form-control"
                                                           id="countryCode"
                                                           name="countryCode"
                                                           value={attributes.countryCode}
                                                           placeholder="Ex : FR"
                                                           required={"required"}
                                                           onChange={(e) => changeHandler(e) }/>
                                                </div>
                                            </div>
                                            <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div className="form-group">
                                                    <label htmlFor="name"> Country name </label>
                                                    <input type="text"
                                                           className="form-control"
                                                           id="countryName"
                                                           name="countryName"
                                                           value={attributes.countryName}
                                                           placeholder="Ex : FRANCE"
                                                           required={"required"}
                                                           onChange={(e) => changeHandler(e) }/>
                                                </div>
                                            </div>
                                            <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div className="form-group">
                                                    <label htmlFor="name"> Zip code </label>
                                                    <input type="text"
                                                           className="form-control"
                                                           id="zipCode"
                                                           name="zipCode"
                                                           value={attributes.zipCode}
                                                           placeholder="Ex : 44000"
                                                           required={"required"}
                                                           onChange={(e) => changeHandler(e) }/>
                                                </div>
                                            </div>
                                            <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div className="form-group">
                                                    <label htmlFor="name"> City </label>
                                                    <input type="text"
                                                           className="form-control"
                                                           id="city"
                                                           name="city"
                                                           value={attributes.city}
                                                           placeholder="Ex : Nantes"
                                                           required={"required"}
                                                           onChange={(e) => changeHandler(e) }/>
                                                </div>
                                            </div>
                                            <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div className="form-group">
                                                    <label htmlFor="name"> Recipient </label>
                                                    <input type="text"
                                                           className="form-control"
                                                           id="recipient"
                                                           name="recipient"
                                                           value={attributes.recipient}
                                                           placeholder="Ex : BLOC OPERATOIRE"
                                                           required={"required"}
                                                           onChange={(e) => changeHandler(e) }/>
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
                </form>
            </div>
        )
}

export default AddCompany