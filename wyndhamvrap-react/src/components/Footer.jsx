import React from "react";
import '../style.css';

const Footer = () => {
    return (
        <div>
            <footer className="footer" style={{height: "40px",borderBottom: "3px solid #7a6e67",minWidth: "910px",display:"flex",alignItems:"center",justifyContent:"space-around"}}>
                <span className="link">Quick Links</span>
                <span className="link">Site Map</span>
                <span className="link">Wyndham Green</span>
                <span className="link">Careers</span>
                <span className="link">Hotel Development</span>
                <span className="link">Affiliate Opportunities</span>
                <span className="link">Contact Us</span>
            </footer>
        </div>
    );
};

export default Footer;