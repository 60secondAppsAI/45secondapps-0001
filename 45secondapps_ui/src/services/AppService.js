import http from "../http-common"; 

class AppService {
  getAllApps(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/app/apps`, searchDTO);
  }

  get(appId) {
    return this.getRequest(`/app/${appId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/app?field=${matchData}`, null);
  }

  addApp(data) {
    return http.post("/app/addApp", data);
  }

  update(data) {
  	return http.post("/app/updateApp", data);
  }
  
  uploadImage(data,appId) {
  	return http.postForm("/app/uploadImage/"+appId, data);
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

export default new AppService();
