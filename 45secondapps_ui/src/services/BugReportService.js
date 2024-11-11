import http from "../http-common"; 

class BugReportService {
  getAllBugReports(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/bugReport/bugReports`, searchDTO);
  }

  get(bugReportId) {
    return this.getRequest(`/bugReport/${bugReportId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/bugReport?field=${matchData}`, null);
  }

  addBugReport(data) {
    return http.post("/bugReport/addBugReport", data);
  }

  update(data) {
  	return http.post("/bugReport/updateBugReport", data);
  }
  
  uploadImage(data,bugReportId) {
  	return http.postForm("/bugReport/uploadImage/"+bugReportId, data);
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

export default new BugReportService();
