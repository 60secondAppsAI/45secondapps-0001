import http from "../http-common"; 

class FeatureService {
  getAllFeatures(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/feature/features`, searchDTO);
  }

  get(featureId) {
    return this.getRequest(`/feature/${featureId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/feature?field=${matchData}`, null);
  }

  addFeature(data) {
    return http.post("/feature/addFeature", data);
  }

  update(data) {
  	return http.post("/feature/updateFeature", data);
  }
  
  uploadImage(data,featureId) {
  	return http.postForm("/feature/uploadImage/"+featureId, data);
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

export default new FeatureService();
