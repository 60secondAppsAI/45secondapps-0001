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
            <component-table
            v-if="components && components.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:components="components"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-components="getAllComponents"
             >

            </component-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import ComponentTable from "@/components/ComponentTable";
import ComponentService from "../services/ComponentService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    ComponentTable,
  },
  data() {
    return {
      components: [],
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
    async getAllComponents(sortBy='componentId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await ComponentService.getAllComponents(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.components.length) {
					this.components = response.data.components;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching components:", error);
        }
        
      } catch (error) {
        console.error("Error fetching component details:", error);
      }
    },
  },
  mounted() {
    this.getAllComponents();
  },
  created() {
    this.$root.$on('searchQueryForComponentsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllComponents();
    })
  }
};
</script>
<style></style>
