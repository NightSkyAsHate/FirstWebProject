import Vue from 'vue'
import Vuex from 'vuex'
import reviewApi from "api/review"
import userApi from "api/user"
import cargoApi from "api/cargo"
import weightApi from "api/weight"
import conditionApi from "api/conditions"

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        profile: frontendData.profile,
        reviews: frontendData.reviews,
        user: '',
        review: '',
        cargosAll: [],
        cargoCurrentUser: [],
        cargo: '',
        weight_types: [],
        conditions: [],
        clients: [],
    },
    getters: {
        getAllWeights: state => {
            return state.weight_types
        }
    },
    mutations: {
        addReviewMutation(state, review){
            state.reviews = [
                ...state.reviews,
                review
                ]
        },
        updateReviewMutation(state, review){
            const updateIndex = state.reviews(item => item.id === review.id)
            state.reviews = [
                ...state.reviews.slice(0, updateIndex),
                review,
                ...state.reviews.slice(updateIndex + 1)
            ]
        },
        removeReviewMutation(state, review){
            const deletionIndex = state.reviews.findIndex(item => item.id === review.id)
            if (deletionIndex > -1){
                state.reviews = [
                    ...state.reviews.slice(0, deletionIndex),
                    ...state.reviews.slice(deletionIndex + 1)
                ]
            }
        },
        redactReviewMutation(state, review){
            state.review = review
        },
        removeAllReviewsMutation(state){
            state.reviews = []
        },
        redactUser(state, user){
            state.user = user
        },
        updateUserMutation(state, user){
            state.profile = user
            frontendData.profile = user
        },
        updateAllCargosMutation(state, cargos){
            state.cargosAll = cargos
        },
        updateUserCargosMutation(state, cargos){
            state.cargoCurrentUser = cargos
        },
        updateAllWeightTypesMutation(state, weight_types){
            state.weight_types = weight_types
        },
        addWeightTypeMutation(state, weight_type){
            state.weight_types = [
                ...state.weight_types,
                weight_type
            ]
        },
        cleanAllWeightTypesMutation(state){
            state.weight_types = []
        },
        setWeightTypesMutation(state, weightTypes){
            state.weight_types = weightTypes
        },
        setConditionsMutation(state, conditions){
            state.conditions = conditions
        },
        setClientsMutation(state, clients){
            state.clients = clients
        },
        addNewCargoToAllMutation(state, cargo){
            state.cargosAll = [
                ...state.cargosAll,
                cargo
            ]
        },
        setAllCargosMutation(state, cargos){
            state.cargosAll = cargos
        },
        removeAllCargosMutation(state){
            state.cargosAll = []
        },
        filterAllCargosByAmount(state, amount){
            state.cargosAll = state.cargosAll.filter(cargo => cargo.amount === Number(amount))
        },
        filterAllCargosByWeightType(state, weight_type){
            state.cargosAll = state.cargosAll.filter(cargo => cargo.weight_type.name === weight_type)
        },
        removeFromAllCargosMutation(state, cargo){
            const deletionIndex = state.cargosAll.findIndex(item => item.id === cargo.id)
            if (deletionIndex > -1){
                state.reviews = [
                    ...state.cargosAll.slice(0, deletionIndex),
                    ...state.cargosAll.slice(deletionIndex + 1)
                ]
            }
        },
        setAllUsersCargosMutation(state, cargos){
            state.cargoCurrentUser = cargos
        },
        filterCargoByUser(state){
            state.cargoCurrentUser = state.cargoCurrentUser.filter(cargo => cargo.user.id === state.profile.id)
        },
        addNewUserCargoMutation(state, cargo){
            state.cargoCurrentUser = [
                ...state.cargoCurrentUser,
                cargo
            ]
        },
        removeAllUserCargosMutation(state){
            state.cargoCurrentUser = []
        }
    },
    actions: {
        async addReviewAction({commit}, review){
            const result = await reviewApi.save(review)
            const data = await result.json()
            commit('addReviewMutation', data)
        },
        async updateReviewAction({commit}, review){
            const result = await reviewApi.update(review)
            const data = await result.json()
            commit('updateReviewMutation', data)
        },
        async removeReviewAction({commit}, review){
            const result = await reviewApi.delete(review.id)
            if (result.ok && result.data === true){
                commit('removeReviewMutation', review)
            }
        },
        async getUserById({commit, state}, userId){
            const result = await reviewApi.getById(userId)
            const data = await result.json()
            commit('redactUser', data)
        },
        async updateUserInfo({commit}, user){
            const result = await userApi.validation(user)
            const data = await result.json()
            if (data.response === true){
                commit('updateUserMutation', user)
                return true
            }else{
                return false
            }
        },
        async getAllCargos({commit}){
            const result = await cargoApi.get()
            const data = await result.json()
            commit('setAllCargosMutation', data)
        },
        async getAllWeightTypes({commit}){
            commit('cleanAllWeightTypesMutation')
            const result = await weightApi.get()
            const data = await result.json()
            commit('setWeightTypesMutation', data)
        },
        async getAllConditions({commit}){
            const result = await conditionApi.get()
            const data = await result.json()
            commit('setConditionsMutation', data)
        },
        async getAllClients({commit}){
            const result = await userApi.get()
            const data = await result.json()
            commit('setClientsMutation', data)
        },
        async addNewCargo({commit}, cargo){
            const result = await cargoApi.save(cargo)
            if (result.ok){
                commit('addNewCargoToAllMutation', cargo)
                return true
            }
            return false
        },
        async findCargoById({commit}, id){
            const result = await cargoApi.getById(id)
            commit('removeAllCargosMutation')
            const data = await result.json()
            commit('addNewCargoToAllMutation', data)
        },
        async findCargoByAmount({commit}, amount){
            const result = await cargoApi.get()
            const data = await result.json()
            commit('setAllCargosMutation', data)
            commit('filterAllCargosByAmount', amount)
        },
        async findCargoByWeightType({commit}, weight_type){
            const result = await cargoApi.get()
            const data = await result.json()
            commit('setAllCargosMutation', data)
            commit('filterAllCargosByWeightType', weight_type)
        },
        async removeCargo({commit}, cargo){
            const result = await cargoApi.remove(cargo.id)
            if (result.ok && result.data === true){
                commit('removeFromAllCargosMutation', cargo)
            }
        },
        async getUsersCargos({commit}){
            const result = await cargoApi.get()
            const data = await result.json()
            commit('setAllUsersCargosMutation', data)
            commit('filterCargoByUser')
        },
        async getCargoById({commit}, id){
            const result = await cargoApi.getById(id)
            commit('removeAllUserCargosMutation')
            const data = await result.json()
            commit('addNewUserCargoMutation', data)
        }
    }
})