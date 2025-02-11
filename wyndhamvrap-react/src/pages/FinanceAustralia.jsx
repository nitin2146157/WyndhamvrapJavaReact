import React from "react";
import '../style.css';
import { useState } from "react";
import { useNavigate } from "react-router-dom";


const FinanceAustralia = () => {
    const [amount, setAmount] = useState("0.0");
    const [contractNumber, setContractNumber] = useState("");
    const [contractNumberError, setContractNumberError] = useState("");
    const [amountError, setAmountError] = useState("");

    const navigate = useNavigate();

    const handleContractNumberChange = (e) => {
        const value = e.target.value;
        if (/^\d{0,12}$/.test(value)) {
            setContractNumber(value);
            setContractNumberError("");
        } else {
            setContractNumberError("Contract number must contain 12 digits.");
        }
    };

    const handleAmountChange = (e) => {
        const value = e.target.value;
        console.log("Entered value:", value); // Debugging line
        if (value === "" || /^\d{1,}(\.\d{0,2})?$/.test(value)) {
            setAmount(value);
            setAmountError("");
        } else {
            setAmountError("Amount Should be single digit or up to two decimal places.");
        }
    };

    
    const handleSubmit = (e) => {
        e.preventDefault();
        let valid = true;
    
        if (contractNumber.length !== 12) {
            setContractNumberError("Contract number must contain 12 digits.");
            valid = false;
        } else {
            setContractNumberError("");
        }
    
        if (!/^\d+(\.\d{2})?$/.test(amount)) {
            setAmountError("Amount Should be single digit or up to two decimal places.");
            valid = false;
        } else if( parseFloat(amount) <= 0){
            setAmountError("Amount should be greater than 0");
            valid = false;
        }
        else {
            setAmountError("");
        }
    
        if (valid) {
            // Proceed with form submission
            navigate("/wyndhamvrap/payments/thankyou")
            console.log("Form submitted with contract number:", contractNumber);
            console.log("Form submitted with amount:", amount);
        }
    };

    return (
        <div>
            <div className="content-container">
                <div style={{ backgroundColor: "#003399", display: "flex", justifyContent: "center", minWidth: "910px", padding: "20px 0" }}>
                    <img src="/images/logo.png" style={{ height: "72px", width: "175px" }} alt="CLUB WYNDHAM Logo" />
                </div>
                <h1 className="finance-title font-size">Finance by Wyndham - Finance payments</h1>
                <div className="payment-container">
                    <form className="payment-form custom-form" onSubmit={handleSubmit}>
                        <div style={{ display: "flex", alignItem: "center", justifyContent: "center", flexDirection: "column" }}>
                            <div className="form-field">
                                <label className="form-label">Contract Number:*</label>
                                <input type="text" className="form-input" style={{ maxWidth: "205px" }} value={contractNumber} onChange={handleContractNumberChange}
                            required/>
                            </div>
                         
                            <p className="form-subtext">(Located on your notice)</p>
                            <p className="error-text">{contractNumberError}</p>


                            <div className="form-field">
                                <label className="form-label">Amount:*</label>
                                <input type="text" className="form-input input-amount" style={{ maxWidth: "205px" }}
                                    value={amount}
                                    onChange={handleAmountChange}
                                    required />
                            </div>
                            <p className="form-subtext">(Australian Dollar only)</p>
                            <p className="error-text">{amountError}</p>
                        </div>
                        <div className="form-field">
                            <button type="submit" className="continue-btn">CONTINUE</button>
                        </div>
                    </form>
                </div>

                {/* Footer Text */}
                <p className="footer-text footer-font-size">© Wyndham Destinations Asia Pacific 2025</p>

            </div>

        </div>

    );
};

export default FinanceAustralia;