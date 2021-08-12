import React, {useState} from "react";
import {APP_API} from "../helpers/AppRequests";
import {userToken} from "../../App";


const AddAddress = () => {

    /**
     * Initial state, where all fields are empty
     * @type {{country: string, streetName: string, town: string, streetNumber: string, postalCode: string}}
     */
    const initialState = {
        streetNumber: '',
        streetName: '',
        postalCode: '',
        town: '',
        country: ''
    }

    const [values, setValues] = useState(initialState)

    /**
     * this function cleans form content
     */
    const handleReset = () => {
        setValues(initialState);
    }

    /**
     * assigns each field content to its attribute
     * @param event content entry
     */
    const changeHandler = (event) => {
        setValues({...values, [event.target.name]: event.target.value})
    }


    /**
     * form submit handler
     * @param event submit
     */
    const handleSubmit = (event) => {
        event.preventDefault();
        const body = APP_API.saveAddress(JSON.stringify(values), userToken);
        body.then(response => {
            handleReset()
        }).catch(onerror => {
            console.error("Promise error: " + onerror)
        })
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
                                            <h3>Add a new address</h3>
                                        </div>
                                        <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                            <div className="form-group">
                                                <label htmlFor="streetNumber">Street number</label>
                                                <input type="text"
                                                       className="form-control"
                                                       id="streetNumber"
                                                       name="streetNumber"
                                                       value={values.streetNumber}
                                                       placeholder="Ex : 2"
                                                       required={"required"}
                                                       onChange={(e) => changeHandler(e)}/>
                                            </div>
                                        </div>
                                        <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                            <div className="form-group">
                                                <label htmlFor="streetName">Street name</label>
                                                <input type="text"
                                                       className="form-control"
                                                       id="streetName"
                                                       name="streetName"
                                                       value={values.streetName}
                                                       placeholder="Ex : Rue Machin"
                                                       required={"required"}
                                                       onChange={(e) => changeHandler(e)}/>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="row gutters">
                                        <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                            <div className="form-group">
                                                <label htmlFor="postalCode">Postal code</label>
                                                <input type="text"
                                                       className="form-control"
                                                       id="postalCode"
                                                       name="postalCode"
                                                       value={values.postalCode}
                                                       placeholder="Ex : 44000"
                                                       required={"required"}
                                                       onChange={(e) => changeHandler(e)}
                                                />
                                            </div>
                                        </div>
                                        <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                            <div className="form-group">
                                                <label htmlFor="town">City</label>
                                                <input type="text"
                                                       className="form-control"
                                                       id="town" name="town"
                                                       placeholder="Ex : Nantes"
                                                       required={"required"}
                                                       value={values.town}
                                                       onChange={(e) => changeHandler(e)}
                                                />
                                            </div>
                                        </div>
                                    </div>
                                    <div className="row gutters">
                                        <div className="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                            <div className="form-group">
                                                <label htmlFor="country">Country</label>
                                                <input type="text"
                                                       className="form-control"
                                                       id="country"
                                                       name="country"
                                                       placeholder="Ex : France"
                                                       required={"required"}
                                                       value={values.country}
                                                       onChange={(e) => changeHandler(e)}
                                                />
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

export default AddAddress