.PHONY: publish

VERSION := $(shell grep '^VERSION_NAME=' gradle.properties | cut -d'=' -f2)
BRANCH := $(shell git rev-parse --abbrev-ref HEAD)
JAVA_HOME := $(shell grep '^javaHome=' gradle.properties | cut -d'=' -f2)

publish:
	@echo "Проверка ветки..."
	@if [ "$(BRANCH)" != "main" ]; then \
		echo "Ошибка: публикация разрешена только из ветки main"; \
		exit 1; \
	fi
	@echo "Сборка библиотеки..."
	export JAVA_HOME=$(JAVA_HOME)
	./gradlew :assemble
	@echo "Публикация на Maven Central..."
	./gradlew publishAllPublicationsToMavenCentralRepository
	@echo "Создание тега v$(VERSION)..."
	git tag v$(VERSION)
	@echo "Проверка изменений..."
	if ! git diff --quiet || ! git diff --cached --quiet; then \
		git add .; \
		git commit -m "Release v$(VERSION)"; \
	fi
	@echo "Пушим коммит и тег..."
	git push origin main
	git push origin v$(VERSION)

