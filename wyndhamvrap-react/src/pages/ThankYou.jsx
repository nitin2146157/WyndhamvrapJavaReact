import React from "react";
import "../style.css"; // Ensure styles are properly imported
 
const ThankYou = () => {
  return (
    <div className="content-container">
      {/* Header */}
      <div
        style={{
          backgroundColor: "#003399",
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          minWidth: "910px",
          padding: "20px 0",
        }}
      >
        <img
          src="/images/logo.png"
          style={{ height: "72px", width: "175px" }}
          alt="CLUB WYNDHAM Logo"
        />
      </div>
 
      {/* Success Message */}
      <div className="thank-you-content">
        <h2 className="thank-you-title">Thank you!</h2>
        <h2 className="thank-you-message">Your payment has been successful.</h2>
 
        {/* Return Button */}
        <button className="return-button" onClick={() => window.location.href = "/dashboard"}>
          Return to Dashboard
        </button>
      </div>
 
      {/* Footer */}
      <div>
            <footer className="footer" style={{height: "40px",color: "#003399",minWidth: "910px",display:"flex",alignItems:"center",justifyContent:"space-around"}}>
                <span className="link">Club Wyndham South Pacific</span>
                <span className="link">Club Wyndham Asia</span>
                <span className="link">Travel +Leisure Co.</span>
                <span className="link">Discovery by Wyndham</span>
            </footer>    
      </div>          
    </div>
  );
};
 
export default ThankYou;