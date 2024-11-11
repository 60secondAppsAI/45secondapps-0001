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
            <appGenerator-table
            v-if="appGenerators && appGenerators.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:appGenerators="appGenerators"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-app-generators="getAllAppGenerators"
             >

            </appGenerator-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import AppGeneratorTable from "@/components/AppGeneratorTable";
import AppGeneratorService from "../services/AppGeneratorService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    AppGeneratorTable,
  },
  data() {
    return {
      appGenerators: [],
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
    async getAllAppGenerators(sortBy='appGeneratorId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await AppGeneratorService.getAllAppGenerators(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.appGenerators.length) {
					this.appGenerators = response.data.appGenerators;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching appGenerators:", error);
        }
        
      } catch (error) {
        console.error("Error fetching appGenerator details:", error);
      }
    },
  },
  mounted() {
    this.getAllAppGenerators();
  },
  created() {
    this.$root.$on('searchQueryForAppGeneratorsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllAppGenerators();
    })
  }
};
</script>
<style></style>
