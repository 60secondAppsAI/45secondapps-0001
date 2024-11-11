import http from "../http-common"; 

class DeveloperService {
  getAllDevelopers(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/developer/developers`, searchDTO);
  }

  get(developerId) {
    return this.getRequest(`/developer/${developerId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/developer?field=${matchData}`, null);
  }

  addDeveloper(data) {
    return http.post("/developer/addDeveloper", data);
  }

  update(data) {
  	return http.post("/developer/updateDeveloper", data);
  }
  
  uploadImage(data,developerId) {
  	return http.postForm("/developer/uploadImage/"+developerId, data);
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

export default new DeveloperService();
