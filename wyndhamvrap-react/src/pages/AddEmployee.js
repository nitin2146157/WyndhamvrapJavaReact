import React, { useState } from "react";
import apiClient from "../api/apiClient";

const AddEmployee = () => {
  const [name, setName] = useState("");
  const [salary, setSalary] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    const newEmployee = { name, salary };

    apiClient
      .post("/employees", newEmployee)
      .then(() => {
        alert("Employee added successfully");
        setName("");
        setSalary("");
      })
      .catch((error) => console.error("Error adding employee:", error));
  };

  return (
    <div>
      <h2>Add Employee</h2>
      <form onSubmit={handleSubmit}>
        <label>Name:</label>
        <input
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
        />
        <label>Salary:</label>
        <input
          type="number"
          value={salary}
          onChange={(e) => setSalary(e.target.value)}
          required
        />
        <button type="submit">Add</button>
      </form>
    </div>
  );
};

export default AddEmployee;
