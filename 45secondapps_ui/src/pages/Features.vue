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
            <feature-table
            v-if="features && features.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:features="features"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-features="getAllFeatures"
             >

            </feature-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import FeatureTable from "@/components/FeatureTable";
import FeatureService from "../services/FeatureService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    FeatureTable,
  },
  data() {
    return {
      features: [],
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
    async getAllFeatures(sortBy='featureId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await FeatureService.getAllFeatures(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.features.length) {
					this.features = response.data.features;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching features:", error);
        }
        
      } catch (error) {
        console.error("Error fetching feature details:", error);
      }
    },
  },
  mounted() {
    this.getAllFeatures();
  },
  created() {
    this.$root.$on('searchQueryForFeaturesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllFeatures();
    })
  }
};
</script>
<style></style>
