import http from "../http-common"; 

class ComponentService {
  getAllComponents(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/component/components`, searchDTO);
  }

  get(componentId) {
    return this.getRequest(`/component/${componentId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/component?field=${matchData}`, null);
  }

  addComponent(data) {
    return http.post("/component/addComponent", data);
  }

  update(data) {
  	return http.post("/component/updateComponent", data);
  }
  
  uploadImage(data,componentId) {
  	return http.postForm("/component/uploadImage/"+componentId, data);
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

export default new ComponentService();
