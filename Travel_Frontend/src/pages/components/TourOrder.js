import * as React from 'react';
import {Link, useLocation} from "react-router-dom";
import Landing from "./landing/Landing";
import Background from "../../assets/img/photos/roomhotel.jpg";
import './TourOrderSCSS.scss';
import {Container} from "reactstrap";
import {useState} from "react";

export function TourOrder() {

    //
    const [payment, setPayment] = useState('1');
    const {state} = useLocation();
    console.log(state)
    // const { id, color } = state;
    return (
        <Landing>
            <div className="checkout-background-overlay">
                <div className="background-overlay">
                    <img className={"image"}
                         alt={"background"}
                         src={Background}
                    />
                </div>
                <div className="background-title">
                    <p>CHECKOUT</p>
                    <div className="navigation">
                        <Link to={`/`} className="navigation-homepage">
                           HOMEPAGE
                        </Link>
                        <span className="vertical-sign">/</span>
                        <span className="navigation-checkout">CHECKOUT</span>
                    </div>
                </div>
            </div>
            <Container>
                <div className="checkout-content">
                    <div className="checkout-wrapper">
                        <div className="checkout-info">
                            <h1 className="checkout-title">Order #66668888</h1>
                            <div className="checkout-table">
                                <div className="checkout-order-tour">
                                    <div className="order-tour-image">
                                        <Link target={'_blank'} to={`/`}>
                                            <img src={Background} alt="tour"/>
                                        </Link>
                                    </div>
                                    <div className="order-tour-detail">
                                        <div className="order-tour-detail-title">
                                            Waterfalls, Geysers and Glacier
                                        </div>
                                        <div className="order-tour-detail-date">
                                            <div className="date">
                                                <span>Date: </span>
                                                <span>17/03/2022</span>
                                            </div>
                                            <div className="time">
                                                <span>Time: </span>
                                                <span>10:00 am</span>
                                            </div>
                                        </div>
                                        <div className="order-tour-detail-duration">
                                            <span>Duration: </span>
                                            <span>7 days</span>
                                        </div>
                                        <div className="order-tour-detail-guest">
                                            <span>Number people: </span>
                                            <span>Adult x1 =
                                                <span>$100</span>
                                            </span>
                                        </div>
                                    </div>
                                    <div className="order-tour-price">
                                        $0
                                    </div>
                                </div>
                                <div className="checkout-order-price">
                                    <div className="total">
                                        <span>Total: </span>
                                        <span>$0</span>
                                    </div>
                                    <div className="amount-paid">
                                        <span>Amount Paid: </span>
                                        <span>$0</span>
                                    </div>
                                    <div className="amount-due">
                                        <span>Amount Due: </span>
                                        <span>$0</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="checkout-form">
                            <h1>Contact Information</h1>
                            <form>
                                <div className="checkout-form-block">
                                    <input
                                        className="checkout-form-input"
                                        type={'text'}

                                    />
                                </div>
                                <div className="checkout-form-block">
                                    <input className="checkout-form-input"/>
                                </div>
                                <div className="checkout-form-block">
                                    <input className="checkout-form-input"/>
                                </div>
                                <div className="checkout-form-block">
                                    <input className="checkout-form-input"/>
                                </div>
                                <div className="checkout-form-block">
                                    <input className="checkout-form-input"/>
                                </div>
                                <h1 className="checkout-payment">
                                    Payment Method
                                </h1>
                                <div className="checkout-payment-tab">
                                    <div className="container-radio">
                                        <input type="radio"
                                               checked={payment === '1'}
                                               value={'1'}
                                               name="radio"
                                               onChange={(e) => setPayment(e.target.value)}
                                        />
                                        Pay Later
                                        <span className="checkmark">
                                        </span>
                                    </div>
                                    <div className="container-radio">
                                        <input type="radio"
                                               name="radio"
                                               value={'2'}
                                               checked={payment === '2'}
                                               onChange={(e) => setPayment(e.target.value)}
                                        />
                                        Paypal
                                        <span className="checkmark">
                                        </span>
                                    </div>

                                </div>
                                <div className="checkout-terms">
                                    <div className="term-check">
                                        <input type={'checkbox'}/>
                                        I read and agree to the terms & conditions
                                    </div>
                                    <div className="term-detail">
                                        You have to edit “Terms & Conditions” page to replace this start content with your own.
                                    </div>
                                </div>
                                <div className="button-submit">
                                    <button type="submit">Complete My Order</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </Container>
        </Landing>
    );
}