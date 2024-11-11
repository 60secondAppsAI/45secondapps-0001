import http from "../http-common"; 

class BillingService {
  getAllBillings(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/billing/billings`, searchDTO);
  }

  get(billingId) {
    return this.getRequest(`/billing/${billingId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/billing?field=${matchData}`, null);
  }

  addBilling(data) {
    return http.post("/billing/addBilling", data);
  }

  update(data) {
  	return http.post("/billing/updateBilling", data);
  }
  
  uploadImage(data,billingId) {
  	return http.postForm("/billing/uploadImage/"+billingId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new BillingService();
