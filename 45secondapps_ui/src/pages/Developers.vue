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
            <developer-table
            v-if="developers && developers.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:developers="developers"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-developers="getAllDevelopers"
             >

            </developer-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import DeveloperTable from "@/components/DeveloperTable";
import DeveloperService from "../services/DeveloperService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    DeveloperTable,
  },
  data() {
    return {
      developers: [],
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
    async getAllDevelopers(sortBy='developerId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await DeveloperService.getAllDevelopers(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.developers.length) {
					this.developers = response.data.developers;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching developers:", error);
        }
        
      } catch (error) {
        console.error("Error fetching developer details:", error);
      }
    },
  },
  mounted() {
    this.getAllDevelopers();
  },
  created() {
    this.$root.$on('searchQueryForDevelopersChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllDevelopers();
    })
  }
};
</script>
<style></style>
