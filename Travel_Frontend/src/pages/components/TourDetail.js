import React, {useEffect, useState} from 'react';
import Landing from "./landing/Landing";
import {getTourDetail} from "../../redux/actions/tourAction";
import {connect} from "react-redux";
import {useHistory, useParams} from "react-router-dom";
import {Carousel} from "react-responsive-carousel";
import {
    BsArrowLeftCircle,
    BsArrowRightCircle,
    BsClock,
    BsPeople,
    BsGeoAlt,
    BsCompass,
    BsStar,
    BsCheckCircle,
    BsXCircle,
    BsGeoAltFill,
    BsCaretDownFill
} from "react-icons/bs";
import {
    Container, Input, Collapse, Card, CardBody
} from "reactstrap";
import image from "../../assets/img/photos/austria.jpg";
import './TourDetailSCSS.scss';

import { useFormik } from 'formik';


function TourDetail(props) {

    //router
    let history = useHistory();
    const {tourId} = useParams()

    //state
    const [dataTourDetail, setDataTourDetail] = useState();

    const numberPeople = [1,2,3,4,5,6,7,8,9,10];
    const priceByAge = {
        adult: 100,
        youth: 80,
        children: 0
    }

    //call API tour detail
    useEffect(() => {
        props.getTourDetail(tourId);
    },[])

    const {tourDetail} = props.tourData;

    //set lai data moi khi {tourListData} thay doi
    useEffect(() => {
        setDataTourDetail(tourDetail);
    }, [tourDetail]);

    console.log("Rerender",dataTourDetail)

    const formik = useFormik({
        initialValues: {
            start_date: new Date(),
            number_adult: 0,
            number_youth: 0,
            number_children: 0,
        },
        onSubmit: values => {
            alert(JSON.stringify(values, null, 2));
            const totalPrice = formik.values.number_adult * priceByAge.adult
                                + formik.values.number_youth * priceByAge.youth
                                + formik.values.number_children * priceByAge.children
            history.push(`/checkout/?orderId=${123}`,{
                state: {
                    values,
                    totalPrice
                }
            })

        },
    });


    return (
        <Landing>
            <section className="py-6">
                <Container>
                    <div className="tour-detail__title">
                        <h1>Essence Of Viet Nam South To North</h1>
                    </div>
                    <div className="tour-detail__geo">
                        <BsGeoAlt/>
                        <div>Bryce Canyon National Park, USA</div>
                    </div>
                </Container>
            </section>
            <section className="tour-detail__carousel">
                <Carousel
                    showIndicators={true}
                    // animationHandler="fade"
                    // swipeable={false}
                    // autoPlay
                    showStatus={false}
                    showThumbs={false}
                    infiniteLoop
                    renderArrowNext={(onClickHandler,hasNext,label) =>
                        hasNext && (
                            <div onClick={onClickHandler} title={label} className="arrownext-custom ">
                                <BsArrowRightCircle size={45} color={'#CADEE6'} fontWeight={100}/>
                            </div>
                        )
                    }
                    renderArrowPrev={(onClickHandler,hasPrev,label) =>
                        hasPrev && (
                            <div onClick={onClickHandler} title={label} className="arrowprev-custom ">
                                <BsArrowLeftCircle size={45} color={'#CADEE6'} fontWeight={100} />
                            </div>
                        )
                    }
                    renderIndicator={(onClickHandler, isSelected, index, label) => {
                        if(isSelected) {
                            return (
                                <li
                                    style={{ width: 8, height: 8, display: 'inline-block', background: '#000' }}
                                    aria-label={`Selected: ${label} ${index + 1}`}
                                    title={`Selected: ${label} ${index + 1}`}
                                />
                            );
                        }
                        return(
                            <li
                                style={{ width: 8, height: 8, display: 'inline-block', background: '#fff' }}
                                onClick={onClickHandler}
                                onKeyDown={onClickHandler}
                                value={index}
                                key={index}
                                role="button"
                                tabIndex={0}
                                title={`${label} ${index + 1}`}
                                aria-label={`${label} ${index + 1}`}
                            />
                        );
                    }
                    }
                    className="intro-carousel"
                >
                    {dataTourDetail?.imageList?.map((url) => (
                            <img key={url.id} src={url.imageUrl} className="tour-detail__image" alt="tour"/>
                    ))}
                </Carousel>
            </section>
            <div className="tour-detail__info">
                <Container>
                    <div className="item-wrap">
                        <div className="field">
                            <div className="item-info">
                                <div className="label">Price</div>
                                <div className="value">From <span>100$</span></div>
                            </div>
                        </div>
                        <div className="field">
                            <BsClock className="item-icon"/>
                            <div className="item-info">
                                <div className="label">Duration</div>
                                <div className="value">7 days</div>
                            </div>
                        </div>
                        <div className="field">
                            <BsPeople className="item-icon"/>
                            <div className="item-info">
                                <div className="label">Max People</div>
                                <div className="value">30</div>
                            </div>
                        </div>
                        <div className="field">
                            <BsCompass className="item-icon"/>
                            <div className="item-info">
                                <div className="label">Tour Type</div>
                                <div className="value">Beaches, Hiking</div>
                            </div>
                        </div>
                        <div className="field">
                            <div className="item-info">
                                <div className="label">Reviews</div>
                                <div className="value"><BsStar /><span>10 reviews</span></div>
                            </div>
                        </div>
                    </div>
                </Container>
            </div>

        {/*    */}
            <div className="tour-detail__content">
                <Container>
                    <div className="content-row">
                        <div className="column-left-wrap">
                            <div className="column-widget-wrap">
                                <h1>Overview</h1>
                                <div className="overview">
                                    <div className="overview-content">
                                        <p>Included in National Geographic Canada’s 50 Places of a Lifetime, the Discovery Islands are a sea kayaking paradise, and as you will discover, one of the West Coast’s best kept secrets. You’ll find no better sea kayaking vacation throughout remote islands on BC’s central coast.

                                            Comprised of a dozen islands in the Discovery Passage between Vancouver Island and the mainland in British Columbia, we will take you kayaking throughout this sparsely inhabited remote group of islands on this kayaking tour.

                                            Paddling around the islands provides the ideal way to get close to nature and be completely enveloped in the beauty of towering trees, remote beaches, and mountains. Discover the wildlife that inhabit this region and the secrets of British Columbia’s rich and plentiful inter-tidal life while exploring magical waterways.‍
                                        </p>
                                        <p>&nbsp;</p>
                                        <h5>TOUR HIGHLIGHT</h5>
                                        <ul>
                                            <li>Be enveloped in the beauty of towering trees, remote beaches, and mountains of the Canadian wilderness</li>
                                            <li>Have close encounters with BC’s coastal wildlife; sea lions, seals, pacific white sided dolphins, otters, birds and some of the largest starfish in the world</li>
                                            <li>Be on the lookout for Humpback whales that have made a strong resurgence in the Discovery Islands</li>
                                            <li>Coastal cuisine designed by a local gourmet chef</li>
                                            <li>Quality expedition tents & warm sleeping bags all included in the price of the trip</li>
                                            <li>High Staff to Guest Ratio</li>
                                            <li>Small Group Size</li>
                                            <li>Accommodations on Quadra Island the night before you launch at one of Quadra’s beautiful resorts</li>
                                        </ul>
                                    </div>

                                </div>
                            </div>
                            <div className="divider">
                            </div>
                            <div className="column-widget-container">
                                <h1>Included/Excluded</h1>
                                <div className="column-content">
                                    <ul className="included">
                                        <li className="list-item">
                                            <span className="icon"><BsCheckCircle /></span>
                                            <span>Specialized bilingual guid</span>
                                        </li>
                                    </ul>
                                    <ul className="excluded">
                                        <li className="list-item">
                                            <span className="icon"><BsXCircle /></span>
                                            <span> 5 Star Accommodation</span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div className="tour-detail-plan">
                                <h2>TOUR PLAN</h2>
                                <div>
                                    <div className="collapse-title"
                                         onClick={function noRefCheck(){}}
                                         style={{
                                             marginBottom: '1rem'
                                         }}>
                                        <h4 className="title-plan">
                                            <span><BsGeoAltFill/></span>
                                            <strong>Day 1</strong>
                                            South Iceland
                                        </h4>
                                        <BsCaretDownFill />
                                    </div>
                                    <Collapse>
                                        <Card>
                                            <CardBody>
                                                Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident.
                                            </CardBody>
                                        </Card>
                                    </Collapse>
                                </div>

                            </div>
                        </div>
                        <div className="column-right-wrap">
                            <div className="column-widget-wrap">
                                <form className="form-booking" onSubmit={formik.handleSubmit}>
                                    <h2>Book This Tour</h2>
                                    <div className="start-date">
                                        <h4>From:</h4>
                                        <Input
                                            type="date"
                                            id="start_date"
                                            name="start_date"
                                            onChange={formik.handleChange}
                                            value={formik.values.start_date}
                                            className='input-date'/>
                                    </div>
                                    <div className="time">
                                        <h4>Time:</h4>
                                        <span>8:00</span>
                                    </div>
                                    <div className="people">
                                        <h4>Number people: </h4>
                                        <div className="people-booking">
                                            <div className="input-select-title">
                                                <div className="input-select-title-value">
                                                    Adult (18+ years)
                                                    <span className={"currency_amount"}>$100</span>
                                                </div>
                                                <div className="input-select-wrapper">
                                                    <Input
                                                        type={'select'}
                                                        id="number_adult"
                                                        name="number_adult"
                                                        onChange={formik.handleChange}
                                                        value={formik.values.number_adult}
                                                    >
                                                        <option value="">
                                                            0
                                                        </option>
                                                        {numberPeople.map((number, index) => (
                                                            <option key={index}>{number}</option>
                                                        ))}
                                                    </Input>
                                                </div>
                                            </div>
                                            <div className="input-select-title">
                                                <div className="input-select-title-value">
                                                    Youth (13-17+ years)
                                                    <span className={"currency_amount"}>$80</span>
                                                </div>
                                                <div className="input-select-wrapper">
                                                    <Input
                                                        type={'select'}
                                                        id="number_youth"
                                                        name="number_youth"
                                                        onChange={formik.handleChange}
                                                        value={formik.values.number_youth}
                                                    >
                                                        <option value="">
                                                            0
                                                        </option>
                                                        {numberPeople.map((number, index) => (
                                                            <option key={index}>{number}</option>
                                                        ))}
                                                    </Input>
                                                </div>
                                            </div>
                                            <div className="input-select-title">
                                                <div className="input-select-title-value">
                                                    Children (0-12 years)
                                                    <span className={"currency_amount"}>$0</span>
                                                </div>
                                                <div className="input-select-wrapper">
                                                    <Input
                                                        type={'select'}
                                                        id="number_children"
                                                        name="number_children"
                                                        onChange={formik.handleChange}
                                                        value={formik.values.number_children}
                                                    >
                                                        <option value="">
                                                            0
                                                        </option>
                                                        {numberPeople.map((number, index) => (
                                                            <option key={index}>{number}</option>
                                                        ))}
                                                    </Input>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="total">
                                        <h4>Total:</h4>
                                        <span
                                            type={"text"}
                                            id="total"
                                        >
                                            {formik.values.number_adult * priceByAge.adult
                                            + formik.values.number_youth * priceByAge.youth
                                            + formik.values.number_children * priceByAge.children
                                            }$
                                        </span>
                                    </div>
                                    <div className="button-booking">
                                        <button type={"submit"} className="submit-booking">
                                            Book Now
                                        </button>
                                    </div>
                                </form>

                            </div>
                            <div className="recently-booked">
                                <h2>Last Minute Deals</h2>
                                <div className="tours-booked">
                                    <div className="item-booked">
                                        <img src={image} alt={"item"}/>
                                        <div className="item-description-booked">
                                            <div>7 Days in Costa Rica</div>
                                            <BsStar />
                                            <span>$50</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                </Container>
            </div>
        </Landing>
    );
}

const mapGlobalStateToProps = (state) => ({
    tourData: state.tourReducer,
})
const mapDispatchToProps = {
    getTourDetail
}
export default connect(mapGlobalStateToProps, mapDispatchToProps) (TourDetail);