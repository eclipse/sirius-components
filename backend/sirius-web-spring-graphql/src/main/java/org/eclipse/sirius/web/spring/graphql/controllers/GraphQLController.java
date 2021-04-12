/*******************************************************************************
 * Copyright (c) 2019, 2020 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.web.spring.graphql.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.sirius.web.core.api.UploadFile;
import org.eclipse.sirius.web.spring.graphql.api.GraphQLConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLContext;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

/**
 * The entry point of the GraphQL HTTP API.
 * <p>
 * This endpoint will be available on the API base path prefix with the graphql segment used as a suffix. As such, users
 * will be able to send GraphQL queries to the following URL:
 * </p>
 *
 * <pre>
 * PROTOCOL://DOMAIN.TLD(:PORT)/API_BASE_PATH/graphql
 * </pre>
 *
 * <p>
 * In a development environment, the URL will most likely be:
 * </p>
 *
 * <pre>
 * http://localhost:8080/api/graphql
 * </pre>
 *
 * <p>
 * GraphQL queries can be sent using a POST HTTP request with the following content as the JSON body of the request.
 * </p>
 *
 * <pre>
 * {
 *   "query": "...",
 *   "variables": {
 *     "key": "value"
 *   },
 *   "operationName": "..."
 * }
 * </pre>
 *
 * <p>
 * The result of the execution of the request will be returned using the following JSON data structure:
 * </p>
 *
 * <pre>
 * {
 *   "data": { ... },
 *   "errors": [
 *     { ... }
 *   ]
 * }
 * </pre>
 *
 * @author sbegaudeau
 */
@RestController
@RequestMapping(GraphQLConstants.GRAPHQL_BASE_PATH)
public class GraphQLController {

    private static final String METRIC_NAME = "siriusweb_graphql_http"; //$NON-NLS-1$

    private static final String KIND = "kind"; //$NON-NLS-1$

    private static final String OPERATIONS = "operations"; //$NON-NLS-1$

    private static final String MAP = "map"; //$NON-NLS-1$

    private static final String FIRST_UPLOADED_FILE = "0"; //$NON-NLS-1$

    private static final String MULTIPART_VARIABLES_FILE = "variables.file"; //$NON-NLS-1$

    private static final String VARIABLE_FILE = "file"; //$NON-NLS-1$

    private static final String INPUT_ARGUMENT = "input"; //$NON-NLS-1$

    private final Logger logger = LoggerFactory.getLogger(GraphQLController.class);

    private final ObjectMapper objectMapper;

    private final GraphQL graphQL;

    private final Timer graphQLRequestTimer;

    private final Timer graphQLUploadTimer;

    public GraphQLController(ObjectMapper objectMapper, GraphQL graphQL, MeterRegistry meterRegistry) {
        this.objectMapper = Objects.requireNonNull(objectMapper);
        this.graphQL = Objects.requireNonNull(graphQL);

        // @formatter:off
        this.graphQLRequestTimer = Timer.builder(METRIC_NAME)
                .tag(KIND, "request") //$NON-NLS-1$
                .register(meterRegistry);
        this.graphQLUploadTimer = Timer.builder(METRIC_NAME)
                .tag(KIND, "upload") //$NON-NLS-1$
                .register(meterRegistry);
        // @formatter:on
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> performRequest(@RequestBody GraphQLPayload graphQLPayload, HttpServletRequest request, Principal principal) {
        if (principal == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String query = graphQLPayload.getQuery();
        Map<String, Object> variables = Optional.ofNullable(graphQLPayload.getVariables()).orElse(Map.of());
        String operationName = graphQLPayload.getOperationName();

        // @formatter:off
        GraphQLContext graphQLContext = GraphQLContext.newContext()
                .of(GraphQLConstants.PRINCIPAL, principal)
                .build();

        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                .query(query)
                .variables(variables)
                .operationName(operationName)
                .context(graphQLContext)
                .build();
        // @formatter:on

        long start = System.currentTimeMillis();
        ExecutionResult executionResult = this.graphQL.execute(executionInput);
        long end = System.currentTimeMillis();

        this.graphQLRequestTimer.record(end - start, TimeUnit.MILLISECONDS);

        this.logErrors(executionResult);

        return new ResponseEntity<>(executionResult.toSpecification(), HttpStatus.OK);
    }

    private void logErrors(ExecutionResult executionResult) {
        if (!executionResult.getErrors().isEmpty()) {
            try {
                String stringSpecification = this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(executionResult.toSpecification());
                this.logger.warn(stringSpecification);
            } catch (JsonProcessingException exception) {
                this.logger.error(exception.getMessage(), exception);
            }
        }
    }

    /**
     * Handle a multipart POST request with a GraphQL query.
     *
     * The graphQL query will be send as part of a multipart POST request. Theoretically, we can send n files with the a
     * multipart POST request. Here, we handle only one file (with the zero index) for now.
     *
     * To set the file in the GraphQL ExecutionInput, we put a file variable (set with a {@link UploadFile} instance) in
     * the variable map.
     *
     *
     * @see https://github.com/jaydenseric/graphql-multipart-request-spec
     */
    @PostMapping(path = "/upload")
    public ResponseEntity<Map<String, Object>> uploadDocument(@RequestParam(OPERATIONS) String operations, @RequestParam(MAP) String map, @RequestParam(FIRST_UPLOADED_FILE) MultipartFile file,
            Principal principal) {
        Optional<GraphQLPayload> optionalGraphQLPayload = this.getGraphQLPayload(operations);
        Optional<JsonNode> optionalJsonNode = this.getJsonNode(map);

        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        if (principal == null) {
            responseEntity = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if (optionalGraphQLPayload.isPresent() && optionalJsonNode.isPresent()) {
            GraphQLPayload graphQLPayload = optionalGraphQLPayload.get();
            JsonNode jsonNode = optionalJsonNode.get();

            Optional<Map<String, Object>> optionalVariables = this.getVariables(graphQLPayload, jsonNode, file);

            if (optionalVariables.isPresent()) {
                Map<String, Object> variables = optionalVariables.get();

                // @formatter:off
                GraphQLContext graphQLContext = GraphQLContext.newContext()
                        .of(GraphQLConstants.PRINCIPAL, principal)
                        .build();

                ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                        .query(graphQLPayload.getQuery())
                        .variables(variables)
                        .context(graphQLContext)
                        .build();
                // @formatter:on

                long start = System.currentTimeMillis();
                ExecutionResult executionResult = this.graphQL.execute(executionInput);
                long end = System.currentTimeMillis();

                this.graphQLUploadTimer.record(end - start, TimeUnit.MILLISECONDS);

                this.logErrors(executionResult);

                responseEntity = new ResponseEntity<>(executionResult.toSpecification(), HttpStatus.CREATED);
            }
        }
        return responseEntity;
    }

    private Optional<GraphQLPayload> getGraphQLPayload(String operations) {
        Optional<GraphQLPayload> optionalGraphQLPayload = Optional.empty();
        if (operations != null) {
            try {
                optionalGraphQLPayload = Optional.of(this.objectMapper.readValue(operations, GraphQLPayload.class));
            } catch (IOException exception) {
                this.logger.error(exception.getMessage(), exception);
            }
        } else {
            this.logger.warn("Missing operations parameter"); //$NON-NLS-1$
        }
        return optionalGraphQLPayload;
    }

    private Optional<JsonNode> getJsonNode(String map) {
        Optional<JsonNode> optionalJsonNode = Optional.empty();
        if (map != null) {
            try {
                optionalJsonNode = Optional.of(this.objectMapper.readTree(map));
            } catch (IOException exception) {
                this.logger.error(exception.getMessage(), exception);
            }
        } else {
            this.logger.warn("Missing map parameter"); //$NON-NLS-1$
        }

        return optionalJsonNode;
    }

    /**
     * Creates the map of variables for the evaluation of the GraphQL mutation by adding the necessary content from the
     * multipart file into the variables.
     *
     * @param graphQLPayload
     *            The GraphQL payload used to find the regular variables
     * @param jsonNode
     *            The mapping between the GraphQL variables and the content of the multipart file
     * @param file
     *            The multipart file containing the raw content of the file to upload
     * @return The variables to use to evaluate the request or an empty optional in case of error
     */
    private Optional<Map<String, Object>> getVariables(GraphQLPayload graphQLPayload, JsonNode jsonNode, MultipartFile file) {
        Optional<Map<String, Object>> optionalVariables = Optional.empty();
        String variableName = jsonNode.get(FIRST_UPLOADED_FILE).asText();
        if (variableName.equals(MULTIPART_VARIABLES_FILE)) {
            Map<String, Object> variables = new HashMap<>(graphQLPayload.getVariables());

            Map<Object, Object> inputMap = new HashMap<>();
            Object input = variables.get(INPUT_ARGUMENT);
            if (input instanceof Map<?, ?>) {
                Map<?, ?> rawInputMap = (Map<?, ?>) input;
                rawInputMap.entrySet().stream().forEach(entry -> inputMap.put(entry.getKey(), entry.getValue()));
            }

            if (file != null) {
                try {
                    InputStream inputStream = file.getInputStream();
                    inputMap.put(VARIABLE_FILE, new UploadFile(file.getOriginalFilename(), inputStream));
                    variables.put(INPUT_ARGUMENT, inputMap);

                    optionalVariables = Optional.of(variables);
                } catch (IOException exception) {
                    this.logger.error(exception.getMessage(), exception);
                }
            } else {
                this.logger.warn("Missing multipart file"); //$NON-NLS-1$
            }
        }
        return optionalVariables;
    }
}
