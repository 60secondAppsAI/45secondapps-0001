import http from "../http-common"; 

class FixService {
  getAllFixs(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/fix/fixs`, searchDTO);
  }

  get(fixId) {
    return this.getRequest(`/fix/${fixId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/fix?field=${matchData}`, null);
  }

  addFix(data) {
    return http.post("/fix/addFix", data);
  }

  update(data) {
  	return http.post("/fix/updateFix", data);
  }
  
  uploadImage(data,fixId) {
  	return http.postForm("/fix/uploadImage/"+fixId, data);
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

export default new FixService();
