import Vue from 'vue'

const weight = Vue.resource('/dbWeight/{id}')

export default {
    get: data => weight.get()
}