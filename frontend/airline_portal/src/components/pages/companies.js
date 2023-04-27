import React, { useEffect, useState } from 'react';
import CompaniesView from "../widgets/companyView";
import sendRequest from "../services/ApiService";

function CompaniesPage() {
    const [companies, setCompanies] = useState([]);

    useEffect(() => {
        const fetchCompanies = async () => {
            const data = await sendRequest({
                path: 'api/companies'
            });
            console.log(data);
            setCompanies(data.data);
        }

        fetchCompanies();
    }, []);

    return (
        <div>
            <CompaniesView companies={companies} />
        </div>
    );
}

export default CompaniesPage;
