interface Customer{
    customerId: number,
    firstName: string,
    lastName: string,
    email: string,
    phone: string,
}

export default async function Customers(){

    const response = await fetch('http://localhost:8080/api/customers');
    const customers = await response.json();

    return (
        <>
        <h1>Customers</h1>
        {/* <div>
            {customers.map((customer: Customer) => (
                <div className=" bg-gray-200 m-4 p-4 block text-red-700 border-red-500 rounded-md" key={customer.customerId}>
                    <p>First Name: {customer.firstName}</p>
                    <p>Last Name: {customer.lastName}</p>
                    <p>Email: {customer.email}</p>
                    <p>Phone: {customer.phone}</p>
                </div>
            ))}
        </div> */}
        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Phone</th>
            </tr>
            {customers.map((customer: Customer) => (
                <tr className=" bg-gray-200 text-red-700 border-red-500 rounded-md" key={customer.customerId}>
                    <td> {customer.firstName}</td>
                    <td> {customer.lastName}</td>
                    <td> {customer.email}</td>
                    <td> {customer.phone}</td>
                </tr>
            ))}
        </table>
        </>
    )
}