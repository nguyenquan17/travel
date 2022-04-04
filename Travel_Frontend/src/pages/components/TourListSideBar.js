import React, {useEffect, useState} from 'react';
import Landing from "./landing/Landing";
import ViewList from "./landing/ViewList";
import {Form, Formik, useFormikContext} from "formik";
import * as Yup from "yup";
import './TourListSideBarSCSS.scss';
import {Button, Container, Input} from "reactstrap";
import {BsStarFill} from "react-icons/all";

export const dataDestination = [
    {
        "id":1,
        "destination": "Viet Nam"
    },
    {
        "id":2,
        "destination": "Japan"
    },
    {
        "id":3,
        "destination": "United States"
    },
    {
        "id":4,
        "destination": "Korean"
    },
    {
        "id":5,
        "destination": "France"
    }
]


const FormSearch = (props) => {
    const [destination, setDestination] = useState(null);
    return(
        <>
            <Formik
                initialValues={
                    {
                        location: '',
                        date: ''
                    }
                }
                validationSchema={
                    Yup.object({
                            location: Yup.string()
                                .required('Required'),
                            date: Yup.date()
                                .required('Required'),
                            }
                    )
                }
                onSubmit={(values) => {
                    alert(`${values.location} - ${values.date}`)
                }}
                validateOnChange={false}
                validateOnBlur={false}
            >
                    {({handleChange, values}) => (
                        <Form>
                            <div className="formik-search">
                                <select name="location"
                                       id="location"
                                        onChange={(e) =>{
                                            handleChange(e);
                                            setDestination(e.target.value)
                                        }}
                                        value={values.location}
                                        className="select-destination"
                                >
                                    <option >
                                        Select Destination
                                    </option>
                                    {dataDestination.map((item, index) => (
                                        <option key={index} value={item.destination}>
                                            {item.destination}
                                        </option>
                                    ))}
                                </select>
                                <input
                                    name={"date"}
                                    id={"date"}
                                    type={"date"}
                                    className="date-input"
                                    onChange={handleChange}
                                />
                                <div className="button-search">
                                    <Button
                                        // disabled={isSubmitting}
                                        type="submit"
                                        className="submit-search"
                                    >
                                        Search
                                    </Button>
                                </div>
                            </div>
                        </Form>
                    )}
            </Formik>
        </>
    )
}

const ValueChangeListener = () => {
    const { submitForm, values } = useFormikContext();
    const { priceFrom, priceTo, review } = values;
    console.log(values)
    useEffect(() => {
        if (values) {
            submitForm();
        }
    }, [values, submitForm]);

    return null;
};

const submitMyForm = ({...values}) => {
        console.log(values.priceFrom, values.priceTo, values.review)
    // alert(`${values.priceFrom} - ${values.priceTo}`)
}

const FormFilter = (props) => {

    return (
        <>
            <Formik
                initialValues={
                    {
                        priceFrom: 0,
                        priceTo: 5000,
                        review: [],
                    }
                }
                validationSchema={
                    Yup.object({
                            priceFrom: Yup.number()
                                .min(0, "Must be a positive integer")
                                .required('Required'),
                            priceTo: Yup.number()
                                .min(0, "Must be a positive integer")
                                .required('Required'),
                            review: Yup.array()
                                .min(0, "Must be a positive integer")
                                .required('Required'),
                            // duration: Yup.string()
                            //     .min(0, "Must be a positive integer")
                            //     .required('Required'),

                        }
                    )
                }
                onSubmit={submitMyForm}
                validateOnBlur={false}
                validateOnChange={false}
            >
                {({values, handleChange,errors}) => (
                    <div className="formik-filter">
                        <div className="wrapper-price">
                            <input
                                className="input-price"
                                id="priceFrom"
                                name="priceFrom"
                                type="number"
                                onChange={handleChange}
                                value={values.priceFrom}
                            />
                            <span>-</span>
                            <input
                                className="input-price"
                                id="priceTo"
                                name="priceTo"
                                type="number"
                                onChange={handleChange}
                                value={values.priceTo}
                            />
                        </div>
                        <div className="wrapper-input">
                            <input
                                className="input-review"
                                type="checkbox"
                                id="review"
                                name="review"
                                value={5}
                                onChange={handleChange}
                            />
                            5 <BsStarFill/>
                        </div>
                        <div className="wrapper-input">
                            <input
                                className="input-review"
                                type="checkbox"
                                id="review"
                                name="review"
                                value={4}
                                onChange={handleChange}
                            />
                            4 <BsStarFill/>
                        </div>
                        <div className="wrapper-input">
                            <input
                                className="input-review"
                                type="checkbox"
                                id="review"
                                name="review"
                                value={3}
                                onChange={handleChange}
                            />
                            3 <BsStarFill/>
                        </div>
                        <div className="wrapper-input">
                            <input
                                className="input-review"
                                type="checkbox"
                                id="review"
                                name="review"
                                value={2}
                                onChange={handleChange}
                            />
                            2 <BsStarFill/>
                        </div>
                        <div className="wrapper-input">
                            <input
                                className="input-review"
                                type="checkbox"
                                id="review"
                                name="review"
                                value={1}
                                onChange={handleChange}
                            />
                            1 <BsStarFill/>
                        </div>
                        <ValueChangeListener />
                    </div>
                )}
            </Formik>
        </>
    )
}


export function TourListSideBar() {

    const Form1Callback = (values) => {
        console.log('formcallback1', values);
    }



    return (
        <Landing >
            <Container>
                <div className="wrapper-sidebar">
                    <div className="search-filter-sidebar">
                        <div className="search-form">
                            <div className="search-wrapper">
                                <h2 className="search-title">Search Tours</h2>
                                <FormSearch />
                            </div>
                        </div>
                        <div className="filter-form">
                            <div className="filter-wrapper">
                                <h2 className="filter-title">Filter</h2>
                                <FormFilter />
                            </div>
                        </div>
                    </div>
                    <div className="search-view-list">
                        <ViewList/>
                    </div>
                </div>
            </Container>
        </Landing>
    );
}