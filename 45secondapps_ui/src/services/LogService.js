import http from "../http-common"; 

class LogService {
  getAllLogs(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/log/logs`, searchDTO);
  }

  get(logId) {
    return this.getRequest(`/log/${logId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/log?field=${matchData}`, null);
  }

  addLog(data) {
    return http.post("/log/addLog", data);
  }

  update(data) {
  	return http.post("/log/updateLog", data);
  }
  
  uploadImage(data,logId) {
  	return http.postForm("/log/uploadImage/"+logId, data);
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

export default new LogService();
