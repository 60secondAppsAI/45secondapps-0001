import http from "../http-common"; 

class AppComponentService {
  getAllAppComponents(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/appComponent/appComponents`, searchDTO);
  }

  get(appComponentId) {
    return this.getRequest(`/appComponent/${appComponentId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/appComponent?field=${matchData}`, null);
  }

  addAppComponent(data) {
    return http.post("/appComponent/addAppComponent", data);
  }

  update(data) {
  	return http.post("/appComponent/updateAppComponent", data);
  }
  
  uploadImage(data,appComponentId) {
  	return http.postForm("/appComponent/uploadImage/"+appComponentId, data);
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

export default new AppComponentService();
