import http from "../http-common"; 

class TemplateService {
  getAllTemplates(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/template/templates`, searchDTO);
  }

  get(templateId) {
    return this.getRequest(`/template/${templateId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/template?field=${matchData}`, null);
  }

  addTemplate(data) {
    return http.post("/template/addTemplate", data);
  }

  update(data) {
  	return http.post("/template/updateTemplate", data);
  }
  
  uploadImage(data,templateId) {
  	return http.postForm("/template/uploadImage/"+templateId, data);
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

export default new TemplateService();
