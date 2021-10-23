package com.example.coreapi.mediator

import com.example.coreapi.database.DatabaseProvider

interface ProvidersFacade : MediatorsProvider, DatabaseProvider, AppProvider