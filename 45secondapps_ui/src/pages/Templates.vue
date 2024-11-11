<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <template-table
            v-if="templates && templates.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:templates="templates"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-templates="getAllTemplates"
             >

            </template-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import TemplateTable from "@/components/TemplateTable";
import TemplateService from "../services/TemplateService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    TemplateTable,
  },
  data() {
    return {
      templates: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllTemplates(sortBy='templateId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await TemplateService.getAllTemplates(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.templates.length) {
					this.templates = response.data.templates;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching templates:", error);
        }
        
      } catch (error) {
        console.error("Error fetching template details:", error);
      }
    },
  },
  mounted() {
    this.getAllTemplates();
  },
  created() {
    this.$root.$on('searchQueryForTemplatesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllTemplates();
    })
  }
};
</script>
<style></style>
