import Vue from 'vue'

const conditions = Vue.resource('/dbCondition/{id}')

export default {
    get: data => conditions.get()
}