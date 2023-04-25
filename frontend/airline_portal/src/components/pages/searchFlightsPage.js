import { useParams } from "react-router-dom";


function SearchFlightsPage() {
    let {from, to, date} = useParams();
    console.log(from, ' ', to, ' ', date);
    
}

export default SearchFlightsPage;