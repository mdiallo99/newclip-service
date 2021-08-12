import React, {useState} from "react";
import {APP_API} from "../helpers/AppRequests";
import {userToken} from "../../App";
import {FormControl, InputGroup} from "react-bootstrap";

/**
 * displays a specific kit content (articles)
 * @param props
 * @returns {JSX.Element}
 * @constructor
 */
function KitContent(props) {

    const importImg = (code) => {

        let img;
        try {
            img = require("../../images/" + code + ".png").default
        } catch (e) {
            img = require("../../images/LOGO.png").default
        }
        return img
    }

    const removeElem = (arr, value) => {

        return arr.filter((ele) => {
            return ele !== value;
        });
    }

    const handleSubmit = (values) => {
        const response = APP_API.addArticlesInVouchar(values, userToken);
              response.then(res => {
                  console.log("submit response: ", res.data)
              }).catch(error => {
                  console.error(error)
              })
    }

    const {location} = props
    const {articles} = location.state.kit

    if (articles && articles.length > 0) {

        let selectedArticles = []
        return (
            <>
                <div className="col-12 text-center mb-3 my-sticky">
                    <h3>LISTE DES ARTICLES DU KIT : {location.state.kit.label}</h3>
                        <button type="button"
                                id="finish"
                                name="finish"
                                onClick={() => {
                                    handleSubmit(JSON.stringify(selectedArticles))
                                    console.log(selectedArticles)
                                }}
                                className="btn btn-success btn-lg btn-block"> Valider
                        </button>

                </div> <br/>
                <div className="container-fluid">
                    <div className="row">
                        {articles.map((article, idx) => (
                            <div className="col-12 col-md-6" key={idx}>
                                <div className="card mb-3">
                                    <div className="row g-0">
                                        <div className="col-3">
                                            <img src={importImg(article.articleCode)}
                                                 className="img-fluid"
                                                 alt="User-Profile-Image"/>
                                        </div>
                                        <div className="col-9">
                                            <div className="card-body">
                                                <strong> {" " + article.articleCode} </strong>
                                                {/*<p className="card-text">*/}
                                                {/*     {" " + article.name}*/}
                                                {/*</p>*/}
                                                <p className="card-text">NUMERO DE LOT:
                                                    <strong> {" " + article.number} </strong>
                                                </p>
                                                <p className="card-text">QUANTITE :
                                                    <strong> {" " + article.quantity} </strong>
                                                </p>
                                                <p className="card-text">DATE DE VALIDITE :
                                                    <strong> {" " + article.validityDate} </strong>
                                                </p>
                                            </div>
                                            <div className="btn-secondary col-sm-2">
                                                <input type="checkbox"
                                                       id={idx}
                                                       onClick={() => {
                                                                    const cb = document.getElementById(idx);
                                                                    if(cb.checked){
                                                                        selectedArticles.push(article)
                                                                    }else{
                                                                        selectedArticles = removeElem(selectedArticles, article)
                                                                    }
                                                                    console.log("checked: "+cb.checked+ " "+idx)
                                                            }
                                                       }
                                                />
                                                <label htmlFor="add">Add</label>
                                            </div>
                                        </div>
                                        </div>

                                </div>
                            </div>

                        ))}
                    </div>
                </div>
            </>
        )
    } else {
        return <div>Ce kit est vide !</div>
    }
}

export default KitContent