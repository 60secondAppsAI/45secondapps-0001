import http from "../http-common"; 

class ApplicationDeveloperService {
  getAllApplicationDevelopers(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/applicationDeveloper/applicationDevelopers`, searchDTO);
  }

  get(applicationDeveloperId) {
    return this.getRequest(`/applicationDeveloper/${applicationDeveloperId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/applicationDeveloper?field=${matchData}`, null);
  }

  addApplicationDeveloper(data) {
    return http.post("/applicationDeveloper/addApplicationDeveloper", data);
  }

  update(data) {
  	return http.post("/applicationDeveloper/updateApplicationDeveloper", data);
  }
  
  uploadImage(data,applicationDeveloperId) {
  	return http.postForm("/applicationDeveloper/uploadImage/"+applicationDeveloperId, data);
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

export default new ApplicationDeveloperService();
