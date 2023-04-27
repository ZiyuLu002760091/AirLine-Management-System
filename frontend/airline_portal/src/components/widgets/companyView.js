import React from 'react';

function CompaniesView(props) {
    const { companies } = props;

    return (
        <div>
            <h1>Registered Companies:</h1>
            <table>
                <thead>
                <tr>
                    <th>Company Name</th>
                    <th>API Name</th>
                    <th>API Path</th>
                </tr>
                </thead>
                <tbody>
                {companies.map((company) => (
                    <tr key={company.companyName}>
                        <td>{company.companyName}</td>
                        <td>{company.apiName}</td>
                        <td>{company.apiPath}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default CompaniesView;
