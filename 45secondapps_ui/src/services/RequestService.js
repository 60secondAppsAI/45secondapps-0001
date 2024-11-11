import http from "../http-common"; 

class RequestService {
  getAllRequests(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/request/requests`, searchDTO);
  }

  get(requestId) {
    return this.getRequest(`/request/${requestId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/request?field=${matchData}`, null);
  }

  addRequest(data) {
    return http.post("/request/addRequest", data);
  }

  update(data) {
  	return http.post("/request/updateRequest", data);
  }
  
  uploadImage(data,requestId) {
  	return http.postForm("/request/uploadImage/"+requestId, data);
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

export default new RequestService();
