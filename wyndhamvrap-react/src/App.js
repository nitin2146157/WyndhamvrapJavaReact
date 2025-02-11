import React from "react";
import { Routes, Route } from "react-router-dom";
import FinanceAustralia from "./pages/FinanceAustralia";
import LevyAustralia from "./pages/LevyAustralia";
import Footer from "./components/Footer";
import "./style.css";
import Discovery from "./pages/Discovery";
import LevyNewZealand from "./pages/LevyNewZealand";
import FinanceNewZealand from "./pages/FinanceNewZealand";
import ThankYou from "./pages/ThankYou";
import EmployeeList from "./pages/EmployeeList";

const App = () => {
  return (
    <div className="main-container">
      <div className="content-wrapper">
       
   
        <div className="page-content">
          <Routes>
            <Route path="/wyndhamvrap/payments/add-employee" element={<EmployeeList />} />
            <Route path="/wyndhamvrap/payments/aud-finance" element={<FinanceAustralia />} />
            <Route path="/wyndhamvrap/payments/leviesAustralia" element={<LevyAustralia />} />
            <Route path="/wyndhamvrap/payments/discovery" element={<Discovery />} />
            <Route path="/wyndhamvrap/payments/new-finance" element={<FinanceNewZealand />} />
            <Route path="/wyndhamvrap/payments/leviesNewZealand" element={<LevyNewZealand />} />
            <Route path="/wyndhamvrap/payments/thankyou" element={<ThankYou  />} />
          </Routes>
        </div>
      </div>
      <Footer/>
    </div>
  );
};

export default App;