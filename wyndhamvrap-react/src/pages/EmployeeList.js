import React, { useEffect, useState } from "react";
import apiClient from "../api/apiClient";
import AddEmployee from "./AddEmployee";

const EmployeeList = () => {
  const [employees, setEmployees] = useState([]);

  useEffect(() => {
    // Fetch employees from API
    apiClient
      .get("/employees")
      .then((response) => setEmployees(response.data))
      .catch((error) => console.error("Error fetching employees:", error));
  }, []);

  return (
    <div>
      <h2>Employee List</h2>
      <ul>
        {employees.map((employee) => (
          <li key={employee.id}>
            {employee.name} - ${employee.salary}
          </li>
        ))}
      </ul>
      <AddEmployee />
    </div>
  );
};

export default EmployeeList;
