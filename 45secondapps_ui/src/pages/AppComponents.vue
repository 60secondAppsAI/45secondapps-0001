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
            <appComponent-table
            v-if="appComponents && appComponents.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:appComponents="appComponents"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-app-components="getAllAppComponents"
             >

            </appComponent-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import AppComponentTable from "@/components/AppComponentTable";
import AppComponentService from "../services/AppComponentService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    AppComponentTable,
  },
  data() {
    return {
      appComponents: [],
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
    async getAllAppComponents(sortBy='appComponentId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await AppComponentService.getAllAppComponents(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.appComponents.length) {
					this.appComponents = response.data.appComponents;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching appComponents:", error);
        }
        
      } catch (error) {
        console.error("Error fetching appComponent details:", error);
      }
    },
  },
  mounted() {
    this.getAllAppComponents();
  },
  created() {
    this.$root.$on('searchQueryForAppComponentsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllAppComponents();
    })
  }
};
</script>
<style></style>
