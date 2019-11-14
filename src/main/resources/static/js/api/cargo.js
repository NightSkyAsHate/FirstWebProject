import Vue from 'vue'

const cargos = Vue.resource('/dbCargo/{id}')

export default {
    save: cargo => cargos.save({}, cargo),
    get: data => cargos.get(),
    getById: id => cargos.get({id: id}),
    remove: id => cargos.delete({id: id})
}