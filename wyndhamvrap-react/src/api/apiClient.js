import axios from "axios";
 
const apiClient = axios.create({
  baseURL: "http://localhost:8080", // Replace with your Spring Boot backend URL
  headers: {
    "Content-Type": "application/json",
  },
});
 
export default apiClient;