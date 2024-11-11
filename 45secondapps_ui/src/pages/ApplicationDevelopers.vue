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
            <applicationDeveloper-table
            v-if="applicationDevelopers && applicationDevelopers.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:applicationDevelopers="applicationDevelopers"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-application-developers="getAllApplicationDevelopers"
             >

            </applicationDeveloper-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import ApplicationDeveloperTable from "@/components/ApplicationDeveloperTable";
import ApplicationDeveloperService from "../services/ApplicationDeveloperService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    ApplicationDeveloperTable,
  },
  data() {
    return {
      applicationDevelopers: [],
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
    async getAllApplicationDevelopers(sortBy='applicationDeveloperId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await ApplicationDeveloperService.getAllApplicationDevelopers(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.applicationDevelopers.length) {
					this.applicationDevelopers = response.data.applicationDevelopers;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching applicationDevelopers:", error);
        }
        
      } catch (error) {
        console.error("Error fetching applicationDeveloper details:", error);
      }
    },
  },
  mounted() {
    this.getAllApplicationDevelopers();
  },
  created() {
    this.$root.$on('searchQueryForApplicationDevelopersChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllApplicationDevelopers();
    })
  }
};
</script>
<style></style>
