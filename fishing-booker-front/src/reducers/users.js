import { ACTIONS } from "../actions/users";

const initState = {
    list: []
}

export const users = (state = initState, action) => {
    switch(action.type) {
        case ACTIONS.GET_ALL:
            return {
                ...state,
                list: [...action.payload]
            }

        case ACTIONS.GET_BY_ID:
            return {
                ...state,
                list: [...action.payload]
            }

        case ACTIONS.ADD:
            return {
                ...state,
                list: [...state.list, action.payload]
            }

        case ACTIONS.UPDATE:
            return {
                ...state,
                list: state.list.map(x => x.id === action.payload.id ? action.payload : x)
            }

        case ACTIONS.DELETE:
            return {
                ...state,
                list: state.list.filter(x => x.id !== action.payload)
            }
        
        default:
            return state
    }
}