import http from "../http-common"; 

class AppGeneratorService {
  getAllAppGenerators(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/appGenerator/appGenerators`, searchDTO);
  }

  get(appGeneratorId) {
    return this.getRequest(`/appGenerator/${appGeneratorId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/appGenerator?field=${matchData}`, null);
  }

  addAppGenerator(data) {
    return http.post("/appGenerator/addAppGenerator", data);
  }

  update(data) {
  	return http.post("/appGenerator/updateAppGenerator", data);
  }
  
  uploadImage(data,appGeneratorId) {
  	return http.postForm("/appGenerator/uploadImage/"+appGeneratorId, data);
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

export default new AppGeneratorService();
