package ru.netology.diploma.room

import ru.netology.diploma.Recipe

internal fun RecipeEntity.toModel () = Recipe (
    id=id,
    kitchenOrdinal = kitchenOrdinal,
    recipeName = recipeName,
    content = content,
    isFavorite = isFavorite
)

internal fun Recipe.toEntity () = RecipeEntity (
    id=id,
    kitchenOrdinal = kitchenOrdinal,
    recipeName = recipeName,
    content = content,
    isFavorite = isFavorite
)