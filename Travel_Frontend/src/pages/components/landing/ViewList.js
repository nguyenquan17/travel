import React, {useEffect, useState} from "react";
import {Container, Row} from "reactstrap";
import TourCard from "./TourCard";
import {getAllTourList} from "../../../redux/actions/tourAction";
import {connect} from "react-redux";

const ViewList = (props) => {

    useEffect(() => {
        props.getAllTourList();
    },[])

    const {tourListData} = props.tourData;

    const [dataTourList, setDataTourList] = useState();

    //set lai data moi khi {tourListData} thay doi
    useEffect(() => {
        setDataTourList(tourListData)
    }, [tourListData]);

    console.log(dataTourList)

    return (
        <section className="">
            <h1 className="section-title">Popular Tours</h1>
            <Container>
                <div className="view-list">
                    {dataTourList?.map((tour) => <TourCard key={tour.id} tour={tour}/>)}
                </div>
            </Container>
        </section>
    );
}
const mapGlobalStateToProps = (state) => ({
    tourData: state.tourReducer,
})
const mapDispatchToProps = {
    getAllTourList
}
export default connect(mapGlobalStateToProps, mapDispatchToProps) (ViewList);
