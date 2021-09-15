import React, {useEffect, useState} from "react";
import {APP_API} from "../helpers/AppRequests";
import {userToken} from "../../App";
import {useHistory} from "react-router-dom"
import Loader from "react-loader-spinner";


/**
 * displays a specific kit content (articles)
 * @param props
 * @returns {JSX.Element}
 * @constructor
 */
const  KitContent = (props) => {


    let [articles, setArticles] = useState(null);
    let [isLoading, setIsLoading] = useState(true)
    let [isEmpty, setIsEmpty] = useState(true)

    useEffect(async () => {
        const response = await APP_API.getKitByLabel(props.match.params.kitLabel, userToken);
        const result = await response.data;
        setArticles(result)
        setIsLoading(false)
        setIsEmpty(result.length === 0)
    }, [props.match.params.kitLabel])
    let history = useHistory();

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

        }).catch(error => {
        })
    }


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
    } else if(isEmpty){
        return (
            <div className="position-absolute top-50 start-50 translate-middle">
                <h3>Ce kit est vide</h3>
            </div>
        )
    } else {

        let selectedArticles = []
        return (
            <>
                <div className="col-12 text-center mb-3 my-sticky">
                    <button type="button"
                            id="finish"
                            name="finish"
                            onClick={() => {
                                handleSubmit(JSON.stringify(selectedArticles))
                                alert("Voucher edited and sent by email successfully !")
                                history.push("/profile")
                            }}
                            className="btn btn-success btn-lg btn-block"
                    > Valider
                    </button>

                </div>
                <br/>
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
                                                <p className="card-text">NUMERO DE LOT:
                                                    <strong> {" " + article.number} </strong>
                                                </p>
                                                <p className="card-text">QTE EN STOCK:
                                                    <strong> {" " + article.quantity} </strong>
                                                </p>
                                                <p className="card-text">DATE DE VALIDITE :
                                                    <strong> {" " + article.validityDate} </strong>
                                                </p>
                                            </div>
                                            <div className="btn-secondary col-sm-3">
                                                <input type="checkbox"
                                                       id={idx}
                                                       onClick={() => {
                                                           const cb = document.getElementById(idx);
                                                           if (cb.checked) {
                                                               selectedArticles.push(article)
                                                           } else {
                                                               selectedArticles = removeElem(selectedArticles, article)
                                                           }
                                                       }
                                                       }
                                                />
                                                <label htmlFor="add">Ajouter</label>
                                                <input type="number"
                                                       placeholder="Qte en besoin"
                                                       min="1"
                                                       name="neededQuantity"
                                                       id={idx + "qte"}
                                                       defaultValue="1"
                                                       max={article.quantity}

                                                       onChange={() => {
                                                           article["neededQuantity"] = document.getElementById(idx + "qte").value
                                                       }}
                                                />
                                            </div>
                                            <br/>

                                        </div>
                                    </div>

                                </div>
                            </div>
                        ))
                        }
                    </div>
                </div>
            </>
        )
    }
}

export default KitContent