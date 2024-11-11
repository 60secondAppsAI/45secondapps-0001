import http from "../http-common"; 

class AppFeatureService {
  getAllAppFeatures(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/appFeature/appFeatures`, searchDTO);
  }

  get(appFeatureId) {
    return this.getRequest(`/appFeature/${appFeatureId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/appFeature?field=${matchData}`, null);
  }

  addAppFeature(data) {
    return http.post("/appFeature/addAppFeature", data);
  }

  update(data) {
  	return http.post("/appFeature/updateAppFeature", data);
  }
  
  uploadImage(data,appFeatureId) {
  	return http.postForm("/appFeature/uploadImage/"+appFeatureId, data);
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

export default new AppFeatureService();
